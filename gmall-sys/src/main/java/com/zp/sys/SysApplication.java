package com.zp.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SysApplication
 *
 * @author zhengpanone
 * @since 2022-06-30
 */
@MapperScan("com.zp.sys.mapper")
@SpringBootApplication(scanBasePackages = {"com.zp.common", "com.zp.sys"})
public class SysApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class, args);
    }
}
