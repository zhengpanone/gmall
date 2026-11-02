package com.zp.gmall.module.ai;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
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
    static {
        /**
         * {@link com.zp.gmall.module.infra.InfraServerApplication}
         */
        System.setProperty("nacos.logging.default.config.enabled", "false");
        if (LoggerFactory.getILoggerFactory() instanceof LoggerContext loggerContext) {
            loggerContext.getLogger("com.alibaba.nacos").setLevel(Level.WARN);
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(AiServerApplication.class, args);
    }
}
