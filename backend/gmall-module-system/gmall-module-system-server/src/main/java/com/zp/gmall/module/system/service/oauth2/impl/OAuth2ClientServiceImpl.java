package com.zp.gmall.module.system.service.oauth2.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.annotations.VisibleForTesting;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.common.util.string.StrUtils;
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
import static com.zp.gmall.module.system.enums.ErrorCodeConstants.*;

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
    public OAuth2ClientDO validOAuthClientFromCache(String clientId, String clientSecret, String grantType, Collection<String> scope, String redirectUri) {
        // 校验客户端是否存在
        OAuth2ClientDO client = getOAuth2ClientFromCache(clientId);
        if (client == null) {
            throw exception(OAUTH2_CLIENT_NOT_EXISTS);
        }
        if (CommonStatusEnum.isDisable(client.getStatus())) {
            throw exception(OAUTH2_CLIENT_DISABLE);
        }
        // 校验客户端密钥
        if (StrUtil.isNotEmpty(clientSecret) && ObjectUtil.notEqual(client.getClientSecret(), clientSecret)) {
            throw exception(OAUTH2_CLIENT_CLIENT_SECRET_ERROR);
        }
        // 校验授权方式
        if (StrUtil.isNotEmpty(grantType) && !CollUtil.contains(client.getAuthorizedGrantTypes(), grantType)) {
            throw exception(OAUTH2_CLIENT_AUTHORIZED_GRANT_TYPE_NOT_EXISTS);
        }
        // 校验授权范围
        if (CollUtil.isNotEmpty(scope) && !CollUtil.containsAll(client.getScopes(), scope)) {
            throw exception(OAUTH2_CLIENT_SCOPE_OVER);
        }
        // 校验回调地址
        if (StrUtil.isNotEmpty(redirectUri) && StrUtils.startWithAny(redirectUri, client.getRedirectUris())) {
            throw exception(OAUTH2_CLIENT_REDIRECT_URI_NOT_MATCH, redirectUri);
        }
        return client;
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
