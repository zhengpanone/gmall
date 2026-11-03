package com.zp.gmall.module.trade;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 00:10
 * Version : v1.0.0
 * Description:
 */
@SpringBootApplication
public class TradeApplication {
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
        SpringApplication.run(TradeApplication.class, args);
    }
}
