package com.zp.module.system.api.oauth2;

import com.zp.framework.common.pojo.Result;
import com.zp.module.system.api.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import com.zp.module.system.api.oauth2.dto.OAuth2AccessTokenCreateReqDTO;
import com.zp.module.system.api.oauth2.dto.OAuth2AccessTokenRespDTO;
import com.zp.module.system.service.oauth2.OAuth2TokenService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 20:51
 * Version : v1.0.0
 * Description: OAuth2TokenApi 提供给OpenFeign
 */
// 提供 RESTful API 接口，给 Feign 调用
@RestController
@Validated
public class OAuth2TokenApiImpl implements OAuth2TokenApi {

    @Resource
    private OAuth2TokenService oauth2TokenService;

    @Override
    public Result<OAuth2AccessTokenRespDTO> createAccessToken(OAuth2AccessTokenCreateReqDTO reqDTO) {
        return null;
    }

    @Override
    public Result<OAuth2AccessTokenCheckRespDTO> checkAccessToken(String accessToken) {
        return null;
    }

    @Override
    public Result<OAuth2AccessTokenRespDTO> removeAccessToken(String accessToken) {
        return null;
    }

    @Override
    public Result<OAuth2AccessTokenRespDTO> refreshAccessToken(String refreshToken, String clientId) {
        return null;
    }
}
