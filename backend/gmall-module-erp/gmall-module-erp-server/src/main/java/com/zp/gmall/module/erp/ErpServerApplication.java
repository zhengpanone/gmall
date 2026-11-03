package com.zp.gmall.module.erp;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.erp
 * <p>
 * Description: ERP 启动类
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 4/30/26 9:59 AM
 */
@SpringBootApplication
public class ErpServerApplication {
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
        SpringApplication.run(ErpServerApplication.class, args);
    }
}
