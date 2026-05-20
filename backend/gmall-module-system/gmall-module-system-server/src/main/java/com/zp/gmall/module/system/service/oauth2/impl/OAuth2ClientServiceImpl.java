package com.zp.gmall.module.system.service.oauth2.impl;

import com.google.common.annotations.VisibleForTesting;
import com.zp.gmall.module.system.constant.RedisKeyConstants;
import com.zp.gmall.module.system.controller.admin.oauth2.dto.OAuth2ClientDTO;
import com.zp.gmall.module.system.convert.oauth2.OAuth2ClientConvert;
import com.zp.gmall.module.system.entity.oauth2.OAuth2ClientDO;
import com.zp.gmall.module.system.mapper.oauth2.OAuth2ClientMapper;
import com.zp.gmall.module.system.service.oauth2.IOAuth2ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;

import static com.zp.gmall.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.gmall.module.system.enums.ErrorCodeConstants.OAUTH2_CLIENT_EXISTS;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 19:05
 * Version : v1.0.0
 * Description:
 */
@Service
@Validated
@Slf4j
@RequiredArgsConstructor
public class OAuth2ClientServiceImpl implements IOAuth2ClientService {

    private final OAuth2ClientConvert convertMapper = Mappers.getMapper(OAuth2ClientConvert.class);


    private final OAuth2ClientMapper oauth2ClientMapper;

    @Override
    public void create(OAuth2ClientDTO dto) {

        validateClientIdExists(null, dto.getClientId());
        OAuth2ClientDO client = convertMapper.convert(dto);
        oauth2ClientMapper.insert(client);
    }

    @Cacheable(cacheNames = RedisKeyConstants.OAUTH_CLIENT, key = "#clientId", unless = "#result == null")
    @Override
    public OAuth2ClientDO getOAuth2ClientFromCache(String clientId) {
        return oauth2ClientMapper.selectByClientId(clientId);
    }

    @Override
    public OAuth2ClientDO validOAuthClientFromCache(String clientId) {
        return null;
    }

    @Override
    public OAuth2ClientDO validOAuthClientFromCache(String clientId, String clientSecret, String grantType, Collection<String> scope, String redirectUri) {
        // 校验客户端是否存在

        return null;
    }

    @VisibleForTesting
    void validateClientIdExists(String id, String clientId) {
        OAuth2ClientDO client = oauth2ClientMapper.selectByClientId(clientId);
        if (client == null) {
            throw exception(OAUTH2_CLIENT_EXISTS);
        }
        if (!client.getId().equals(id)) {
            throw exception(OAUTH2_CLIENT_EXISTS);
        }
    }


}
