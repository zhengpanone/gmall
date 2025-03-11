package com.zp.gmall.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * Author : zhengpanone
 * Date : 2025/4/9 11:25
 * Version : v1.0.0
 * Description: Redis+Bus配置
 */
@Slf4j
@Configuration
public class GatewayConfig {

    public void onMessage(Object payload){
        // 监听到 Bus 消息
        log.info("接收到 Bus 消息: {}", payload);
    }

}
