package com.zp.module.system.service.oauth2.impl;

import com.google.common.annotations.VisibleForTesting;
import com.zp.framework.common.pojo.PageResult;
import com.zp.framework.common.util.object.BeanUtils;
import com.zp.module.system.controller.admin.oauth2.dto.client.OAuth2ClientSaveDTO;
import com.zp.module.system.controller.admin.oauth2.dto.token.OAuth2ClientPageDTO;
import com.zp.module.system.dal.dataobject.oauth2.OAuth2ClientDO;
import com.zp.module.system.dao.oauth2.OAuth2ClientMapper;
import com.zp.module.system.service.oauth2.OAuth2ClientService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;

import static com.zp.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.module.system.enums.ErrorCodeConstants.OAUTH2_CLIENT_EXISTS;

/**
 * Author : zhengpanone
 * Date : 2024/8/6 14:43
 * Version : v1.0.0
 * Description:
 */
@Service
@Validated
@Slf4j
public class OAuth2ClientServiceImpl implements OAuth2ClientService {
    @Resource
    private OAuth2ClientMapper oauth2ClientMapper;

    /**
     * 创建 OAuth2 客户端
     *
     * @param createDTO 创建信息
     * @return 编号
     */
    @Override
    public String createOAuth2Client(OAuth2ClientSaveDTO createDTO) {
        validateClientIdExists(null, createDTO.getClientId());
        // 插入
        OAuth2ClientDO client = BeanUtils.toBean(createDTO, OAuth2ClientDO.class);
        oauth2ClientMapper.insert(client);
        return client.getId();
    }

    /**
     * 更新 OAuth2 客户端
     *
     * @param updateDTO 更新信息
     */
    @Override
    public void updateOAuth2Client(OAuth2ClientSaveDTO updateDTO) {

    }

    /**
     * 删除 OAuth2 客户端
     *
     * @param id 编号
     */
    @Override
    public void deleteOAuth2Client(Long id) {

    }

    /**
     * 获得 OAuth2 客户端
     *
     * @param id 编号
     * @return OAuth2 客户端
     */
    @Override
    public OAuth2ClientDO getOAuth2Client(Long id) {
        return null;
    }

    /**
     * 获得 OAuth2 客户端，从缓存中
     *
     * @param clientId 客户端编号
     * @return OAuth2 客户端
     */
    @Override
    public OAuth2ClientDO getOAuth2ClientFromCache(String clientId) {
        return null;
    }

    /**
     * 获得 OAuth2 客户端分页
     *
     * @param pageDTO 分页查询
     * @return OAuth2 客户端分页
     */
    @Override
    public PageResult<OAuth2ClientDO> getOAuth2ClientPage(OAuth2ClientPageDTO pageDTO) {
        return null;
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
    @Override
    public OAuth2ClientDO validOAuthClientFromCache(String clientId, String clientSecret, String authorizedGrantType, Collection<String> scopes, String redirectUri) {
        return null;
    }


    @VisibleForTesting
    void validateClientIdExists(Long id, String clientId) {
        OAuth2ClientDO client = oauth2ClientMapper.selectByClientId(clientId);
        if (client == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的客户端
        if (id == null) {
            throw exception(OAUTH2_CLIENT_EXISTS);
        }
        if (!client.getId().equals(id)) {
            throw exception(OAUTH2_CLIENT_EXISTS);
        }
    }
}
