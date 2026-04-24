package com.zp.gmall.module.system.service.oauth2;

import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 18:54
 * Version : v1.0.0
 * Description:
 */
public interface IOAuth2TokenService {

    /**
     * 创建访问令牌
     * 注意：该流程中，会包含创建刷新令牌的创建
     *
     * 参考 DefaultTokenServices 的 createAccessToken 方法
     *
     * @param userId 用户编号
     * @param userType 用户类型
     * @param clientId 客户端编号
     * @param scopes 授权范围
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO createAccessToken(String userId, Integer userType, String clientId, List<String> scopes);

}
