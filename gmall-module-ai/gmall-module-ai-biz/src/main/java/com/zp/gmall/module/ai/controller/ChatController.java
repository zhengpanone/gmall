package com.zp.gmall.module.ai.controller;

import com.alibaba.cloud.ai.dashscope.api.DashScopeAgentApi;
import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author : zhengpanone
 * Date : 2026/3/17 22:31
 * Version : v1.0.0
 * Description:
 */
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatModel chatModel;


    @GetMapping("/chat")
    public String chat(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        return chatModel.call(prompt);
    }

    @GetMapping("/chatStream")
    public Flux<String> chatStream(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        return chatModel.stream(prompt);
    }

}
