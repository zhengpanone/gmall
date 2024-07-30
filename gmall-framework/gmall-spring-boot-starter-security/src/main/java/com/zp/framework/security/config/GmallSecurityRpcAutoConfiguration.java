package com.zp.framework.security.config;

import com.zp.module.system.api.oauth2.OAuth2TokenApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Author : zhengpanone
 * Date : 2024/7/30 13:39
 * Version : v1.0.0
 * Description: TODO
 */
@AutoConfiguration
@EnableFeignClients(clients = {OAuth2TokenApi.class, // 主要是引入相关的 API 服务
        /*PermissionApi.class*/})
public class GmallSecurityRpcAutoConfiguration {
}
