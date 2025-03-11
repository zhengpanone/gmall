package com.zp.gmall.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Objects;

/**
 * Author : zhengpanone
 * Date : 2025/3/27 13:57
 * Version : v1.0.0
 * Description:
 */
@Slf4j
@SpringBootApplication
public class GatewayServerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(GatewayServerApplication.class, args);
        ConfigurableEnvironment env = run.getEnvironment();
        String serverPort = env.getProperty("server.port");
        String contextPath = Objects.nonNull(env.getProperty("server.servlet.context-path")) ? env.getProperty("server.servlet.context-path") : "";
        log.info("""
                                                \s
                         -------------------------------------------------
                             GatewayServerApplication is running! Access URLs:
                             Local:    http://localhost:{}{}
                             Doc:      http://localhost:{}{}/swagger-ui/index.html
                         -------------------------------------------------
                        \s""",
                serverPort, contextPath,
                serverPort, contextPath
        );

    }
}
