package com.zp.gmall.module.system.service.auth.impl;

import com.zp.gmall.framework.common.enums.UserTypeEnum;
import com.zp.gmall.module.system.controller.admin.auth.dto.AuthLoginDTO;
import com.zp.gmall.module.system.controller.admin.auth.vo.AuthLoginVO;
import com.zp.gmall.module.system.convert.auth.AuthConvert;
import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;
import com.zp.gmall.module.system.entity.user.UserDO;
import com.zp.gmall.module.system.enums.logger.LoginLogTypeEnum;
import com.zp.gmall.module.system.enums.oauth2.OAuth2ClientConstants;
import com.zp.gmall.module.system.service.auth.IAdminAuthService;
import com.zp.gmall.module.system.service.oauth2.IOAuth2TokenService;
import com.zp.gmall.module.system.service.user.IUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.zp.gmall.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.gmall.module.system.enums.ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS;

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

    @Override
    public AuthLoginVO login(AuthLoginDTO authLoginDTO) {
        UserDO user = authenticate(authLoginDTO.getUsername(), authLoginDTO.getPassword());

        return createTokenAfterLoginSuccess(user.getId(),authLoginDTO.getUsername(),LoginLogTypeEnum.LOGIN_USERNAME);
    }

    @Override
    public UserDO authenticate(String username, String password) {
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;
        UserDO user = userService.getUserByUsername(username);
        if(Objects.isNull(user)){
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        return user;
    }



    private AuthLoginVO createTokenAfterLoginSuccess(String userId, String username, LoginLogTypeEnum logType) {
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(userId, getUserType().getValue(),
                OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
        return AuthConvert.INSTANCE.convert(accessTokenDO);
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }
}
