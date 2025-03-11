package com.zp.gmall.gateway.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/4/8 14:00
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "网关服务 - 测试服务")
@RestController
public class TestController {

    @Resource
    private DiscoveryClient discoveryClient;

    @Operation(summary = "查看所有服务")
    @GetMapping("/listDiscoveryClient")
    public List<String> listDiscoveryClient() {
        return discoveryClient.getServices();
    }
}
