package com.zp.gmall.module.system.service.oauth2.impl;

import com.google.common.annotations.VisibleForTesting;
import com.zp.gmall.module.system.controller.admin.oauth2.dto.OAuth2ClientDTO;
import com.zp.gmall.module.system.convert.oauth2.OAuth2ClientConvert;
import com.zp.gmall.module.system.entity.oauth2.OAuth2ClientDO;
import com.zp.gmall.module.system.mapper.oauth2.OAuth2ClientMapper;
import com.zp.gmall.module.system.service.oauth2.IOAuth2ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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


    private final OAuth2ClientMapper oAuth2ClientMapper;

    @Override
    public void create(OAuth2ClientDTO dto) {

        validateClientIdExists(null, dto.getClientId());
        OAuth2ClientDO client = convertMapper.convert(dto);
        oAuth2ClientMapper.insert(client);
    }

    @VisibleForTesting
    void validateClientIdExists(String id, String clientId) {
        OAuth2ClientDO client = oAuth2ClientMapper.selectByClientId(clientId);
        if (client == null) {
            throw exception(OAUTH2_CLIENT_EXISTS);
        }
        if (!client.getId().equals(id)) {
            throw exception(OAUTH2_CLIENT_EXISTS);
        }
    }
}
