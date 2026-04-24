package com.zp.gmall.module.ai.controller.admin;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/qwen3.5")
public class Qwen35Controller {
    private final DashScopeApi dashScopeApi;

    @GetMapping("/chat")
    public String chat(@RequestParam(required = false) String imageUrl, @RequestParam(defaultValue = "你是谁") String text) {
        // 1. 构造消息内容列表
        return "";
    }
}
