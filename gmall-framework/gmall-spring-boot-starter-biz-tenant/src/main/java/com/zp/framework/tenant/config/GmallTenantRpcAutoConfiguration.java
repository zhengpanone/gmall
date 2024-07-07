package com.zp.framework.tenant.config;

import com.zp.framework.tenant.core.rpc.TenantRequestInterceptor;
import com.zp.module.system.api.tenant.TenantApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 17:08
 * Version : v1.0.0
 * Description: TODO
 */
@AutoConfiguration
// 允许使用 yudao.tenant.enable=false 禁用多租户
@ConditionalOnProperty(prefix = "gmall.tenant", value = "enable", matchIfMissing = true)
// 主要是引入相关的 API 服务
@EnableFeignClients(clients = TenantApi.class)
public class GmallTenantRpcAutoConfiguration {

    @Bean
    public TenantRequestInterceptor tenantRequestInterceptor() {
        return new TenantRequestInterceptor();
    }
}
