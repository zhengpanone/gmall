package com.zp.framework.apilog.config;

import com.zp.module.infra.api.logger.ApiAccessLogApi;
import com.zp.module.infra.api.logger.ApiErrorLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Author : zhengpanone
 * Date : 2024/1/10 10:08
 * Version : v1.0.0
 * Description: API日志使用到Feign的配置项
 */
@AutoConfiguration
@EnableFeignClients(clients = {ApiAccessLogApi.class, ApiErrorLogApi.class}) // 主要引入相关的API服务
public class GmallApiLogRpcAutoConfiguration {
}
