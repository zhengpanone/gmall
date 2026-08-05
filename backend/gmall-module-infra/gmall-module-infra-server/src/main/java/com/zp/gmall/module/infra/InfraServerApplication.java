package com.zp.gmall.module.infra;

import com.zp.gmall.framework.file.config.GmallFileAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Author : zhengpanone
 * Date : 2024/1/9 15:22
 * Version : v1.0.0
 
 */
@SpringBootApplication
@Import(GmallFileAutoConfiguration.class)
public class InfraServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfraServerApplication.class, args);
    }
}
