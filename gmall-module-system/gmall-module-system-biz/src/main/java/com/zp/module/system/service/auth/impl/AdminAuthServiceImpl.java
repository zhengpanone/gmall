package com.zp.module.system.service.auth.impl;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.annotations.VisibleForTesting;
import com.xingyuv.captcha.model.common.ResponseModel;
import com.xingyuv.captcha.model.vo.CaptchaVO;
import com.xingyuv.captcha.service.CaptchaService;
import com.zp.framework.common.enums.CommonStatusEnum;
import com.zp.framework.common.enums.UserTypeEnum;
import com.zp.framework.common.util.monitor.TracerUtils;
import com.zp.framework.common.util.servlet.ServletUtils;
import com.zp.framework.common.util.validation.ValidationUtils;
import com.zp.module.system.controller.admin.auth.dto.AuthLoginDTO;
import com.zp.module.system.controller.admin.auth.vo.AuthLoginVO;
import com.zp.module.system.convert.auth.AuthConvert;
import com.zp.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import com.zp.module.system.dal.dataobject.user.AdminUserDO;
import com.zp.module.system.api.logger.dto.LoginLogCreateDTO;
import com.zp.module.system.enums.logger.LoginLogTypeEnum;
import com.zp.module.system.enums.logger.LoginResultEnum;
import com.zp.module.system.enums.oauth2.OAuth2ClientConstants;
import com.zp.module.system.service.auth.AdminAuthService;
import com.zp.module.system.service.logger.LoginLogService;
import com.zp.module.system.service.member.MemberService;
import com.zp.module.system.service.oauth2.OAuth2TokenService;
import com.zp.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.zp.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.module.system.enums.ErrorCodeConstants.*;

/**
 * Author : zhengpanone
 * Date : 2023/11/11 17:43
 * Version : v1.0.0
 * Description AdminAuthService 实现类
 */
@Service
@Slf4j
public class AdminAuthServiceImpl implements AdminAuthService {
    @Resource
    private AdminUserService userService;
    @Resource
    private Validator validator;
    @Resource
    private CaptchaService captchaService;
    @Resource
    private MemberService memberService;
    @Resource
    private OAuth2TokenService oAuth2TokenService;
    @Resource
    private LoginLogService loginLogService;

    /**
     * 验证码的开关，默认为true
     */
    @Value("${gmall.captcha.enable:false}")
    private Boolean captchaEnable;

    /**
     * 验证码+密码。如果通过则返回用户
     *
     * @param username 账号
     * @param password 密码
     * @return
     */
    @Override
    public AdminUserDO authenticate(String username, String password) {
        final LoginLogTypeEnum loginTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;
        // 校验账号是否存在
        AdminUserDO user = userService.getUserByUsername(username);
        if (user == null) {
            createLoginLog(null, username, loginTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            createLoginLog(user.getId(), username, loginTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (CommonStatusEnum.isDisable(user.getStatus())) {
            createLoginLog(user.getId(), username, loginTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }
        return user;
    }

    /**
     * 账号登录
     *
     * @param authLoginDTO 登录信息
     * @return 登录结果
     */
    @Override
    public AuthLoginVO login(AuthLoginDTO authLoginDTO) {
        // 校验验证码
        validateCaptcha(authLoginDTO);
        // 使用账号密码进行登录
        AdminUserDO user = authenticate(authLoginDTO.getUsername(), authLoginDTO.getPassword());
        //  如果 socialType 非空，说明需要绑定社交用户
        if (authLoginDTO.getSocialType() != null) {
            // TODO
        }
        // 创建Token令牌,记录登录日志
        return createTokenAfterLoginSuccess(user.getId(), authLoginDTO.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME);
    }

    /**
     * 基于token退出登录
     *
     * @param token   token
     * @param logType 登出类型
     */
    @Override
    public void logout(String token, Integer logType) {
        // 删除访问令牌
        OAuth2AccessTokenDO accessToken = oAuth2TokenService.removeAccessToken(token);
        if (accessToken == null) {
            return;
        }
        // 删除成功，则记录登出日志
        createLogoutLog(accessToken.getUserId(), accessToken.getUserType(), logType);

    }


    @VisibleForTesting
    void validateCaptcha(AuthLoginDTO dto) {
        // 如果验证码关闭，则不进行校验
        if (!captchaEnable) {
            return;
        }
        // 校验验证码
        ValidationUtils.validate(validator, dto, AuthLoginDTO.CodeEnableGroup.class);
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(dto.getCaptchaVerification());
        ResponseModel response = captchaService.verification(captchaVO);
        // 验证不通过
        if (!response.isSuccess()) {
            // 创建登录失败日志（验证码不正确)
            createLoginLog(null, dto.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME, LoginResultEnum.CAPTCHA_CODE_ERROR);
            throw exception(AUTH_LOGIN_CAPTCHA_CODE_ERROR, response.getRepMsg());
        }

    }

    private void createLoginLog(String userId, String username, LoginLogTypeEnum loginLogTypeEnum, LoginResultEnum loginResultEnum) {
        // 插入登录日志
        LoginLogCreateDTO loginLogCreateDTO = new LoginLogCreateDTO();
        loginLogCreateDTO.setLogType(loginLogTypeEnum.getType());
        loginLogCreateDTO.setTraceId(TracerUtils.getTraceId());
        loginLogCreateDTO.setUserId(userId);
        loginLogCreateDTO.setUserType(getUserType().getValue());
        loginLogCreateDTO.setUsername(username);
        loginLogCreateDTO.setUserAgent(ServletUtils.getUserAgent());
        loginLogCreateDTO.setUserIp(ServletUtils.getClientIP());
        loginLogCreateDTO.setResult(loginResultEnum.getResult());
        loginLogService.createLoginLog(loginLogCreateDTO);
        // 更新最后登录时间
        if (userId != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResultEnum.getResult())) {
            userService.updateUserLogin(userId, ServletUtils.getClientIP());
        }
    }

    private void createLogoutLog(String userId, Integer userType, Integer logType) {
        LoginLogCreateDTO reqDTO = new LoginLogCreateDTO();
        reqDTO.setLogType(logType);
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(userType);
        if (ObjectUtil.equals(getUserType().getValue(), userType)) {
            reqDTO.setUsername(getUsername(userId));
        } else {
            reqDTO.setUsername(memberService.getMemberUserMobile(userId));
        }
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(LoginResultEnum.SUCCESS.getResult());
        loginLogService.createLoginLog(reqDTO);
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }

    private String getUsername(String userId) {
        if (userId == null) {
            return null;
        }
        AdminUserDO user = userService.getUser(userId);
        return user != null ? user.getUsername() : null;
    }

    private AuthLoginVO createTokenAfterLoginSuccess(String userId, String username, LoginLogTypeEnum logType) {
        // 插入登录日志
        createLoginLog(userId, username, logType, LoginResultEnum.SUCCESS);
        // 创建访问令牌
        OAuth2AccessTokenDO accessToken = oAuth2TokenService.createAccessToken(userId, getUserType().getValue(), OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
        return AuthConvert.INSTANCE.convert(accessToken);

    }
}
