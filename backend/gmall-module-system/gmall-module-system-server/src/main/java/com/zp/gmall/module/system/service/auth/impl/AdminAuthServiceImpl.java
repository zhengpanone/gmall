package com.zp.gmall.module.system.service.auth.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.annotations.VisibleForTesting;
import com.xingyuv.captcha.model.common.ResponseModel;
import com.xingyuv.captcha.model.vo.CaptchaVO;
import com.xingyuv.captcha.service.CaptchaService;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.common.enums.UserTypeEnum;
import com.zp.gmall.framework.common.util.monitor.TracerUtils;
import com.zp.gmall.framework.common.util.servlet.ServletUtils;
import com.zp.gmall.module.system.api.logger.dto.LoginLogDTO;
import com.zp.gmall.module.system.controller.admin.auth.dto.AuthLoginDTO;
import com.zp.gmall.module.system.controller.admin.auth.vo.AuthLoginVO;
import com.zp.gmall.module.system.controller.admin.auth.vo.AuthPermissionInfoVO;
import com.zp.gmall.module.system.controller.admin.user.vo.AdminUserVO;
import com.zp.gmall.module.system.convert.auth.AuthConvert;
import com.zp.gmall.module.system.entity.config.ConfigDO;
import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;
import com.zp.gmall.module.system.entity.permission.MenuDO;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import com.zp.gmall.module.system.entity.user.UserDO;
import com.zp.gmall.module.system.enums.logger.LoginLogTypeEnum;
import com.zp.gmall.module.system.enums.logger.LoginResultEnum;
import com.zp.gmall.module.system.enums.oauth2.OAuth2ClientConstants;
import com.zp.gmall.module.system.service.auth.IAdminAuthService;
import com.zp.gmall.module.system.service.config.IConfigService;
import com.zp.gmall.module.system.service.log.ILoginLogService;
import com.zp.gmall.module.system.service.oauth2.IOAuth2TokenService;
import com.zp.gmall.module.system.service.permission.IMenuService;
import com.zp.gmall.module.system.service.permission.IPermissionService;
import com.zp.gmall.module.system.service.permission.IRoleService;
import com.zp.gmall.module.system.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.zp.gmall.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.gmall.framework.common.util.collection.CollectionUtils.convertSet;
import static com.zp.gmall.module.system.enums.ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS;
import static com.zp.gmall.module.system.enums.ErrorCodeConstants.AUTH_LOGIN_CAPTCHA_CODE_ERROR;
import static com.zp.gmall.module.system.enums.ErrorCodeConstants.AUTH_LOGIN_USER_DISABLED;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminAuthServiceImpl implements IAdminAuthService {

    private final IOAuth2TokenService oauth2TokenService;

    private final IUserService userService;

    private final IRoleService roleService;

    private final ILoginLogService loginLogService;

    private final IPermissionService permissionService;

    private final IMenuService menuService;

    private final IConfigService configService;

    private final CaptchaService captchaService;

    @Override
    public AuthLoginVO login(AuthLoginDTO dto) {
        validateCaptcha(dto);
        UserDO user = authenticate(dto.getUsername(), dto.getPassword());
        return createTokenAfterLoginSuccess(user.getId(), dto.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME);
    }

    @Override
    public UserDO authenticate(String username, String password) {
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;
        UserDO user = userService.getUserByUsername(username);
        if (Objects.isNull(user)) {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!userService.verifyPassword(password, user.getPassword())) {
            createLoginLog(user.getId(), username, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (CommonStatusEnum.isDisable(user.getStatus())) {
            createLoginLog(user.getId(), username, logTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }
        return user;
    }

    @Override
    public AuthPermissionInfoVO getPermissionInfo(String userId) {
        AdminUserVO user = userService.getById(userId);
        if (user == null) {
            return null;
        }
        Set<String> roleIds = permissionService.getUserRoleIdListByUserId(userId);
        if (CollUtil.isEmpty(roleIds)) {
            return AuthConvert.INSTANCE.convert(user, Collections.emptyList(), Collections.emptyList());
        }
        List<RoleDO> roleList = roleService.listByIds(roleIds);
        roleList.removeIf(role -> CommonStatusEnum.isDisable(role.getStatus()));

        Set<String> menuIdList = permissionService.getRoleMenuIdListByRoleId(convertSet(roleList, RoleDO::getId));
        List<MenuDO> menuList = menuService.listByIds(menuIdList);

        return AuthConvert.INSTANCE.convert(user, roleList, menuList);
    }

    @VisibleForTesting
    void validateCaptcha(AuthLoginDTO dto) {
        ConfigDO config = configService.getByKey("CAPTCHA_ENABLE");
        if (config == null || !"true".equalsIgnoreCase(config.getConfigValue())) {
            return;
        }
        if (StrUtil.isBlank(dto.getCaptchaVerification())) {
            createLoginLog(null, dto.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME, LoginResultEnum.CAPTCHA_NOT_FOUND);
            throw exception(AUTH_LOGIN_CAPTCHA_CODE_ERROR, "captchaVerification is blank");
        }

        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(dto.getCaptchaVerification());
        ResponseModel responseModel = captchaService.verification(captchaVO);
        if (responseModel == null || !responseModel.isSuccess()) {
            createLoginLog(null, dto.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME, LoginResultEnum.CAPTCHA_CODE_ERROR);
            String errorMessage = responseModel == null ? "captcha verification failed" : responseModel.getRepMsg();
            throw exception(AUTH_LOGIN_CAPTCHA_CODE_ERROR, errorMessage);
        }
    }

    private AuthLoginVO createTokenAfterLoginSuccess(String userId, String username, LoginLogTypeEnum logType) {
        createLoginLog(userId, username, logType, LoginResultEnum.SUCCESS);
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(userId, getUserType().getValue(),
                OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
        return AuthConvert.INSTANCE.convert(accessTokenDO);
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }

    private void createLoginLog(String userId, String username, LoginLogTypeEnum logTypeEnum, LoginResultEnum loginResultEnum) {
        LoginLogDTO loginLogDTO = new LoginLogDTO();
        loginLogDTO.setLogType(logTypeEnum.getType())
                .setTraceId(TracerUtils.getTraceId())
                .setUserId(userId)
                .setUserType(getUserType().getValue())
                .setUsername(username)
                .setUserAgent(ServletUtils.getUserAgent())
                .setUserIp(ServletUtils.getClientIP())
                .setResult(loginResultEnum.getResult());

        loginLogService.create(loginLogDTO);
    }
}
