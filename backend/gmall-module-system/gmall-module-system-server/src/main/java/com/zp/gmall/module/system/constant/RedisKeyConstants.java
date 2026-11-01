package com.zp.gmall.module.system.constant;

import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-20
 */
public interface RedisKeyConstants {

    /**
     * OAuth2 客户端的缓存
     * <p>
     * KEY 格式：oauth_client:{id}
     * VALUE 数据类型：String 客户端信息
     */
    String OAUTH_CLIENT = "oauth_client";

    /**
     * 访问令牌的缓存
     * <p>
     * KEY 格式：oauth2_access_token:{token}
     * VALUE 数据类型：String 访问令牌信息 {@link OAuth2AccessTokenDO}
     * <p>
     * 由于动态过期时间，使用 RedisTemplate 操作
     */
    String OAUTH2_ACCESS_TOKEN = "oauth2_access_token:%s";

    /**
     * 系统配置的缓存
     * <p>
     * KEY 格式：config:{id} 或 config:{configKey}
     * VALUE 数据类型：ConfigVO 配置信息
     */
    String CONFIG = "config";

    /**
     * 角色的缓存
     * <p>
     * KEY 格式：role:{id}
     * VALUE 数据类型：RoleDO 角色信息
     */
    String ROLE = "role";
}
