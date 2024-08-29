package com.zp.module.system.service.oauth2;

import com.zp.framework.common.pojo.PageResult;
import com.zp.module.system.controller.admin.oauth2.dto.client.OAuth2ClientSaveDTO;
import com.zp.module.system.controller.admin.oauth2.dto.token.OAuth2ClientPageDTO;
import com.zp.module.system.dal.dataobject.oauth2.OAuth2ClientDO;
import jakarta.validation.Valid;

import java.util.Collection;

/**
 * Author : zhengpanone
 * Date : 2024/8/6 10:37
 * Version : v1.0.0
 * Description: OAuth2.0 Client Service 接口
 * 从功能上，和 JdbcClientDetailsService 的功能，提供客户端的操作
 */
public interface OAuth2ClientService {
    /**
     * 创建 OAuth2 客户端
     *
     * @param createDTO 创建信息
     * @return 编号
     */
    String createOAuth2Client(@Valid OAuth2ClientSaveDTO createDTO);

    /**
     * 更新 OAuth2 客户端
     *
     * @param updateDTO 更新信息
     */
    void updateOAuth2Client(@Valid OAuth2ClientSaveDTO updateDTO);

    /**
     * 删除 OAuth2 客户端
     *
     * @param id 编号
     */
    void deleteOAuth2Client(Long id);

    /**
     * 获得 OAuth2 客户端
     *
     * @param id 编号
     * @return OAuth2 客户端
     */
    OAuth2ClientDO getOAuth2Client(Long id);

    /**
     * 获得 OAuth2 客户端，从缓存中
     *
     * @param clientId 客户端编号
     * @return OAuth2 客户端
     */
    OAuth2ClientDO getOAuth2ClientFromCache(String clientId);

    /**
     * 获得 OAuth2 客户端分页
     *
     * @param pageDTO 分页查询
     * @return OAuth2 客户端分页
     */
    PageResult<OAuth2ClientDO> getOAuth2ClientPage(OAuth2ClientPageDTO pageDTO);

    /**
     * 从缓存中，校验客户端是否合法
     *
     * @return 客户端
     */
    default OAuth2ClientDO validOAuthClientFromCache(String clientId) {
        return validOAuthClientFromCache(clientId, null, null, null, null);
    }

    /**
     * 从缓存中，校验客户端是否合法
     * <p>
     * 非空时，进行校验
     *
     * @param clientId            客户端编号
     * @param clientSecret        客户端密钥
     * @param authorizedGrantType 授权方式
     * @param scopes              授权范围
     * @param redirectUri         重定向地址
     * @return 客户端
     */
    OAuth2ClientDO validOAuthClientFromCache(String clientId, String clientSecret, String authorizedGrantType,
                                             Collection<String> scopes, String redirectUri);
}
