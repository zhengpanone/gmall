package com.zp.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * MebmerApplcation
 *
 * @author zhengpanone
 * @since 2022-06-25
 */
// 开启feign远程调用服务
@EnableFeignClients(basePackages = "com.zp.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }
}
