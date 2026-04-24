package com.zp.gmall.module.ai.controller;

import com.zp.gmall.module.ai.service.IChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

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

    private final IChatService chatService;


    /**
     * 简单聊天
     *
     * @param prompt
     * @return
     */
    @GetMapping("/simple")
    public String chat(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        return chatService.dashChat(prompt);
    }

    @GetMapping("/chatHistory")
    public String chatHistory(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        List<Message> messages = new ArrayList<>();
        messages.add(new SystemMessage("你是一个Java专家"));
        messages.add(new UserMessage("什么是Spring Boot"));
        messages.add(new AssistantMessage("Spring Boot是..."));
        messages.add(new UserMessage(prompt)); // 它的优点呢？
        return chatService.chat(messages);
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        return chatService.stream(prompt);
    }

}
