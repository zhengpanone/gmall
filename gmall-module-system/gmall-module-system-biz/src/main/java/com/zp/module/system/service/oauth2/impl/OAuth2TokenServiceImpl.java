package com.zp.module.system.service.oauth2.impl;

import com.zp.framework.common.pojo.PageResult;
import com.zp.module.system.controller.admin.oauth2.vo.token.OAuth2AccessTokenPageReqVO;
import com.zp.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import com.zp.module.system.service.oauth2.OAuth2TokenService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2024/7/30 12:51
 * Version : v1.0.0
 * Description: TODO
 */
@Service
public class OAuth2TokenServiceImpl implements OAuth2TokenService {
    @Override
    public OAuth2AccessTokenDO createAccessToken(Long userId, Integer userType, String clientId, List<String> scopes) {
        return null;
    }

    @Override
    public OAuth2AccessTokenDO refreshAccessToken(String refreshToken, String clientId) {
        return null;
    }

    @Override
    public OAuth2AccessTokenDO getAccessToken(String accessToken) {
        return null;
    }

    @Override
    public OAuth2AccessTokenDO checkAccessToken(String accessToken) {
        return null;
    }

    @Override
    public OAuth2AccessTokenDO removeAccessToken(String accessToken) {
        return null;
    }

    @Override
    public PageResult<OAuth2AccessTokenDO> getAccessTokenPage(OAuth2AccessTokenPageReqVO reqVO) {
        return null;
    }
}
