package com.zp.gmall.module.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : zhengpanone
 * Date : 2026/3/17 21:32
 * Version : v1.0.0
 * Description: Ai服务启动类
 */
@SpringBootApplication
public class AiServerApplication {
    public static void main(String[] args) {
        // 禁用 Nacos 自带的 logback 配置（否则它会在 Nacos Config / Discovery 初始化时各加载一次 nacos-logback12.xml，
        // 与主应用的 LoggerContext 抢 CONFIG_LOG_FILE / NAMING_LOG_FILE / REMOTE_LOG_FILE，报 earlier_fa_collision，
        // 后续还会持续刷 "Attempted to append to non started appender"）。
        // 必须在 SpringApplication.run 之前设置：Nacos 客户端日志配置是在环境准备阶段初始化的。
        // 等价的启动参数写法：-Dnacos.logging.default.config.enabled=false
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(AiServerApplication.class, args);
    }
}
