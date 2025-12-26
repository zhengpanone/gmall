package com.zp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GmallGatewayServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GmallGatewayServerApplication.class, args);
    }
}
