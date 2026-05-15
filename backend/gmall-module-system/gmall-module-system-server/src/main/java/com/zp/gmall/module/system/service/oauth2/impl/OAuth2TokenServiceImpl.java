package com.zp.gmall.module.system.service.oauth2.impl;

import com.zp.gmall.framework.common.biz.oauth2.dto.OAuth2AccessTokenPageDTO;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;
import com.zp.gmall.module.system.mapper.oauth2.OAuth2AccessTokenMapper;
import com.zp.gmall.module.system.mapper.oauth2.OAuth2RefreshTokenMapper;
import com.zp.gmall.module.system.service.oauth2.IOAuth2ClientService;
import com.zp.gmall.module.system.service.oauth2.IOAuth2TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 19:03
 * Version : v1.0.0
 * Description:
 */
@RequiredArgsConstructor
@Service
public class OAuth2TokenServiceImpl implements IOAuth2TokenService {

    private final OAuth2AccessTokenMapper oAuth2AccessTokenMapper;

    private final OAuth2RefreshTokenMapper oAuth2RefreshTokenMapper;

    private final IOAuth2ClientService oAuth2ClientService;


    @Override
    public OAuth2AccessTokenDO createAccessToken(String userId, String userType, String clientId, List<String> scopes) {

        return new OAuth2AccessTokenDO();
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
    public void removeAccessToken(Long userId, Integer userType) {

    }

    @Override
    public PageResult<OAuth2AccessTokenDO> getAccessTokenPage(OAuth2AccessTokenPageDTO dto) {
        return null;
    }

    @Override
    public Integer cleanRefreshToken(Integer exceedDay, Integer deleteLimit) {
        return 0;
    }

    @Override
    public Integer cleanAccessToken(Integer exceedDay, Integer deleteLimit) {
        return 0;
    }
}
