package com.zp.gmall.module.system.service.oauth2.impl;

import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;
import com.zp.gmall.module.system.service.oauth2.IOAuth2TokenService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 19:03
 * Version : v1.0.0
 * Description:
 */
@Service
public class OAuth2TokenServiceImpl implements IOAuth2TokenService {

    @Override
    public OAuth2AccessTokenDO createAccessToken(Long userId, Integer userType, String clientId, List<String> scopes) {

        return null;
    }
}
