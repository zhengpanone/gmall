package com.zp.module.infra.framework.rpc.config;

import com.zp.module.system.api.user.AdminUserApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Author : zhengpanone
 * Date : 2024/1/10 11:21
 * Version : v1.0.0
 * Description:
 */
@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = AdminUserApi.class)
public class RpcConfiguration {
}
