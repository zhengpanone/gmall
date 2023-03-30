package com.zp.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootAdminApplication
 *
 * @author zhengpanone
 * @since 2022-07-26
 */
@EnableAdminServer // 引入 Spring Boot Admin Server 配置
@SpringBootApplication
public class SpringBootAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminApplication.class, args);
    }
}
//9910cd0a-20c7-46bb-88e5-867bc8ded94b