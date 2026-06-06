package com.zp.gmall.module.system.constant;

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
