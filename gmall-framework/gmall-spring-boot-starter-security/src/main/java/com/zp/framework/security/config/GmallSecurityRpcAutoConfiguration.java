package com.zp.framework.security.config;

import com.zp.framework.security.core.rpc.LoginUserRequestInterceptor;
import com.zp.module.system.api.oauth2.OAuth2TokenApi;
import com.zp.module.system.api.permission.PermissionApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Author : zhengpanone
 * Date : 2024/7/30 13:39
 * Version : v1.0.0
 * Description:
 */
@AutoConfiguration
@EnableFeignClients(clients = {OAuth2TokenApi.class, // 主要是引入相关的 API 服务
        PermissionApi.class})
public class GmallSecurityRpcAutoConfiguration {

    @Bean
    public LoginUserRequestInterceptor loginUserRequestInterceptor() {
        return new LoginUserRequestInterceptor();
    }
}
