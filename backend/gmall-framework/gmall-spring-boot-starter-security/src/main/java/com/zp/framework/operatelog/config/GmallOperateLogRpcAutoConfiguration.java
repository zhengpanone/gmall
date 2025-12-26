package com.zp.framework.operatelog.config;

import com.zp.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Author : zhengpanone
 * Date : 2024/8/30 09:40
 * Version : v1.0.0
 * Description: OperateLog 使用到 Feign 的配置项
 */
@AutoConfiguration
@EnableFeignClients(clients = {OperateLogApi.class}) // 主要是引入相关的 API 服务
public class GmallOperateLogRpcAutoConfiguration {
}
