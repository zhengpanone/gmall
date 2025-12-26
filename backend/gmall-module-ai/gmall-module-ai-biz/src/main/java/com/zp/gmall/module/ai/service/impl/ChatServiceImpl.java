package com.zp.gmall.module.ai.service.impl;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.zp.gmall.module.ai.service.IChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements IChatService {

    private final ChatClient chatClient;

    private final DashScopeChatModel dashScopeChatModel;


    @Override
    public String chat(String prompt) {
        return chatClient.prompt().user(prompt).call().content();
    }

    @Override
    public String dashChat(String prompt) {
        return dashScopeChatModel.call(prompt);
    }

    @Override
    public String chat(List<Message> history) {
        Prompt prompt = new Prompt(history);
        ChatClient.CallResponseSpec response = chatClient.prompt(prompt).call();
        return response.content();
    }

    @Override
    public Flux<String> stream(String prompt) {
        return chatClient.prompt().user(prompt).stream().content();
    }
}
