package com.zp.gmall.module.system.service.oauth2;

import com.zp.gmall.framework.common.biz.oauth2.dto.OAuth2AccessTokenPageDTO;
import com.zp.gmall.framework.common.domain.vo.PageResult;
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
     * <p>
     * 参考 DefaultTokenServices 的 createAccessToken 方法
     *
     * @param userId   用户编号
     * @param userType 用户类型
     * @param clientId 客户端编号
     * @param scopes   授权范围
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO createAccessToken(String userId, String userType, String clientId, List<String> scopes);

    /**
     * 刷新访问令牌
     * <p>
     * 参考 DefaultTokenServices 的 refreshAccessToken 方法
     *
     * @param refreshToken 刷新令牌
     * @param clientId     客户端编号
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO refreshAccessToken(String refreshToken, String clientId);

    /**
     * 获得访问令牌
     * <p>
     * 参考 DefaultTokenServices 的 getAccessToken 方法
     *
     * @param accessToken 访问令牌
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO getAccessToken(String accessToken);

    /**
     * 校验访问令牌
     *
     * @param accessToken 访问令牌
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO checkAccessToken(String accessToken);

    /**
     * 移除访问令牌
     * 注意：该流程中，会移除相关的刷新令牌
     * <p>
     * 参考 DefaultTokenServices 的 revokeToken 方法
     *
     * @param accessToken 刷新令牌
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO removeAccessToken(String accessToken);

    /**
     * 移除访问令牌
     * 注意：该流程中，会移除相关的刷新令牌
     * <p>
     * 参考 DefaultTokenServices 的 revokeToken 方法
     *
     * @param userId   用户编号
     * @param userType 用户类型
     */
    void removeAccessToken(String userId, String userType);

    /**
     * 获得访问令牌分页
     *
     * @param dto 请求
     * @return 访问令牌分页
     */
    PageResult<OAuth2AccessTokenDO> getAccessTokenPage(OAuth2AccessTokenPageDTO dto);

    /**
     * 清理过期 exceedDay 天的刷新令牌
     *
     * @param exceedDay   过期多少天就进行清理
     * @param deleteLimit 清理的间隔条数
     */
    Integer cleanRefreshToken(Integer exceedDay, Integer deleteLimit);

    /**
     * 清理过期 exceedDay 天的访问令牌
     *
     * @param exceedDay   过期多少天就进行清理
     * @param deleteLimit 清理的间隔条数
     */
    Integer cleanAccessToken(Integer exceedDay, Integer deleteLimit);
}
