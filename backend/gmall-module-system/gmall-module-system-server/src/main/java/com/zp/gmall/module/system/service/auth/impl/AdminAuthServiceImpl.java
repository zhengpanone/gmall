package com.zp.gmall.module.system.service.auth.impl;

import com.google.common.annotations.VisibleForTesting;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.common.enums.UserTypeEnum;
import com.zp.gmall.framework.common.util.monitor.TracerUtils;
import com.zp.gmall.framework.common.util.servlet.ServletUtils;
import com.zp.gmall.module.system.api.logger.dto.LoginLogDTO;
import com.zp.gmall.module.system.controller.admin.auth.dto.AuthLoginDTO;
import com.zp.gmall.module.system.controller.admin.auth.vo.AuthLoginVO;
import com.zp.gmall.module.system.convert.auth.AuthConvert;
import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;
import com.zp.gmall.module.system.entity.user.UserDO;
import com.zp.gmall.module.system.enums.logger.LoginLogTypeEnum;
import com.zp.gmall.module.system.enums.logger.LoginResultEnum;
import com.zp.gmall.module.system.enums.oauth2.OAuth2ClientConstants;
import com.zp.gmall.module.system.service.auth.IAdminAuthService;
import com.zp.gmall.module.system.service.log.ILoginLogService;
import com.zp.gmall.module.system.service.oauth2.IOAuth2TokenService;
import com.zp.gmall.module.system.service.user.IUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.zp.gmall.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.gmall.module.system.enums.ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS;
import static com.zp.gmall.module.system.enums.ErrorCodeConstants.AUTH_LOGIN_USER_DISABLED;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 18:47
 * Version : v1.0.0
 * Description:
 */
@Service
public class AdminAuthServiceImpl implements IAdminAuthService {

    @Resource
    private IOAuth2TokenService oauth2TokenService;
    @Resource
    private IUserService userService;
    @Resource
    private ILoginLogService loginLogService;

    @Override
    public AuthLoginVO login(AuthLoginDTO dto) {
        validateCaptcha(dto);
        // 使用帐号密码登录
        UserDO user = authenticate(dto.getUsername(), dto.getPassword());
        // TODO
        // 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(user.getId(), dto.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME);
    }

    @Override
    public UserDO authenticate(String username, String password) {
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;
        UserDO user = userService.getUserByUsername(username);
        if (Objects.isNull(user)) {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!userService.verifyPassword(user.getId(), password)) {
            createLoginLog(user.getId(), username, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (CommonStatusEnum.isDisable(user.getStatus())) {
            createLoginLog(user.getId(), username, logTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }
        return user;
    }

    @VisibleForTesting
    private void validateCaptcha(AuthLoginDTO dto) {
        // TODO 校验验证码
    }


    private AuthLoginVO createTokenAfterLoginSuccess(String userId, String username, LoginLogTypeEnum logType) {
        // 插入登陆日志
        createLoginLog(userId, username, logType, LoginResultEnum.SUCCESS);
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(userId, getUserType().getValue(),
                OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
        return AuthConvert.INSTANCE.convert(accessTokenDO);
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }


    private void createLoginLog(String userId, String username, LoginLogTypeEnum logTypeEnum, LoginResultEnum loginResultEnum) {
        // 插入登录日志
        LoginLogDTO loginLogDTO = new LoginLogDTO();
        loginLogDTO.setLogType(logTypeEnum.getType())
                .setTraceId(TracerUtils.getTraceId())
                .setUserId(userId)
                .setUserType(getUserType().getValue())
                .setUsername(username)
                .setUserAgent(ServletUtils.getUserAgent())
                .setUserIp(ServletUtils.getClientIP())
                .setResult(loginResultEnum.getResult())
        ;

        loginLogService.create(loginLogDTO);
    }
}
