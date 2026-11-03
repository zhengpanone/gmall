package com.zp.gmall.module.mp;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author : zhengpanone
 * Date : 2024/1/10 14:53
 * Version : v1.0.0
 * Description:
 */
@SpringBootApplication
public class MpServerApplication {
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
        SpringApplication.run(MpServerApplication.class, args);
    }
}
