package com.zp.gmall.module.system.convert.oauth2;

import com.zp.gmall.module.system.controller.admin.oauth2.dto.OAuth2ClientDTO;
import com.zp.gmall.module.system.controller.admin.oauth2.vo.OAuth2ClientVO;
import com.zp.gmall.module.system.entity.oauth2.OAuth2ClientDO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-15
 */
@Mapper(componentModel = "spring")
public interface OAuth2ClientConvert {

    OAuth2ClientConvert INSTANCE = Mappers.getMapper(OAuth2ClientConvert.class);

    @BeanMapping(ignoreByDefault = true)
    OAuth2ClientVO convert(OAuth2ClientDO oAuth2ClientDO);

    @Mapping(source = "clientName", target = "name")
    @Mapping(source = "clientLogo", target = "logo")
    @Mapping(source = "clientDescription", target = "description")
    @Mapping(source = "accessTokenValidity", target = "accessTokenValiditySeconds")
    @Mapping(source = "refreshTokenValidity", target = "refreshTokenValiditySeconds")
    @Mapping(target = "redirectUris", expression = "java(split(oAuth2ClientDTO.getRedirectUri()))")
    @Mapping(target = "authorizedGrantTypes", expression = "java(split(oAuth2ClientDTO.getGrantType()))")
    @Mapping(target = "scopes", expression = "java(split(oAuth2ClientDTO.getScope()))")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "autoApproveScopes", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "resourceIds", ignore = true)
    @Mapping(target = "additionalInformation", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedTime", ignore = true)
    OAuth2ClientDO convert(OAuth2ClientDTO oAuth2ClientDTO);

    default List<String> split(String value) {
        if (value == null || value.isBlank()) {
            return List.of();
        }
        return Arrays.stream(value.split(","))
                .map(String::trim)
                .filter(item -> !item.isEmpty())
                .toList();
    }
}
