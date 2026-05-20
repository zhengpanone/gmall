package com.zp.gmall.module.system.service.oauth2;

import com.zp.gmall.module.system.controller.admin.oauth2.dto.OAuth2ClientDTO;
import com.zp.gmall.module.system.entity.oauth2.OAuth2ClientDO;

import java.util.Collection;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 19:05
 * Version : v1.0.0
 * Description: OAuth2客户端服务
 */
public interface IOAuth2ClientService {

    /**
     * 创建OAuth2 客户端
     *
     * @param dto 客户端信息
     */
    void create(OAuth2ClientDTO dto);

    OAuth2ClientDO getOAuth2ClientFromCache(String clientId);

    /**
     * 从缓存中校验OAuth2客户端信息
     *
     * @param clientId 客户端编号
     * @return 客户端信息
     */
    default OAuth2ClientDO validOAuthClientFromCache(String clientId) {
        return validOAuthClientFromCache(clientId, null, null, null, null);
    }

    /**
     * 从缓存中校验OAuth2客户端信息
     *
     * @param clientId     客户端编号
     * @param clientSecret 客户端密钥
     * @param grantType    授权类型
     * @param scope        授权范围
     * @param redirectUri  重定向URI
     * @return 客户端信息
     */
    OAuth2ClientDO validOAuthClientFromCache(String clientId, String clientSecret, String grantType, Collection<String> scope, String redirectUri);
}
