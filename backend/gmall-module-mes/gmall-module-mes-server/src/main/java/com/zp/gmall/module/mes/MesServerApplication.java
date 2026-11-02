package com.zp.gmall.module.mes;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MesServerApplication {
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
        SpringApplication.run(MesServerApplication.class, args);
    }
}
