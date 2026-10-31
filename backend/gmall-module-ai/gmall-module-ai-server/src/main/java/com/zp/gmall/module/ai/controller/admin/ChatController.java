package com.zp.gmall.module.ai.controller.admin;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.ai.service.IChatService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final OllamaApi ollamaApi;

    /**
     * 简单聊天
     *
     * @param prompt
     * @return
     */
    @GetMapping("/chat")
    public String chat(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        return chatService.chat(prompt);
    }

    @GetMapping("/chatHistory")
    public String chatHistory(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        List<Message> messages = new ArrayList<>();
        messages.add(new SystemMessage("你是一个Java专家"));
        messages.add(new UserMessage("什么是Spring Boot"));
        messages.add(new AssistantMessage("Spring Boot是..."));
        messages.add(new UserMessage(prompt)); // 它的优点呢？
        return chatService.chatHistory(messages);
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        return chatService.stream(prompt);
    }

    @Operation(summary = "列出Ollama模型")
    @GetMapping("/listOllamaModels")
    public Result<List<Map<String, Object>>> listOllamaModels() {
        OllamaApi.ListModelResponse response = ollamaApi.listModels();
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.models());
        List<Map<String, Object>> models = new ArrayList<>();
        response.models().forEach(model -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("model", model.model());
            map.put("size", model.size());
            map.put("modifiedAt", model.modifiedAt());
            models.add(map);
        });
        Assertions.assertFalse(response.models().isEmpty(), "本地应至少有一个模型");
        return Result.ok(models);

    }

}
