package com.zp.gmall.module.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Author : zhengpanone
 * Date : 2025/3/27 13:44
 * Version : v1.0.0
 * Description:
 */
@Slf4j
@SpringBootApplication
public class InfraServerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(InfraServerApplication.class, args);
        ConfigurableEnvironment env = run.getEnvironment();
        log.info("""
                                                \s
                         -------------------------------------------------
                             InfraServerApplication is running! Access URLs:
                             Local:    http://localhost:{}{}
                             Doc:      http://localhost:{}{}/swagger-ui/index.html
                         -------------------------------------------------
                        \s""",
                env.getProperty("server.port"), env.getProperty("server.servlet.context-path"),
                env.getProperty("server.port"), env.getProperty("server.servlet.context-path")
        );
    }
}
