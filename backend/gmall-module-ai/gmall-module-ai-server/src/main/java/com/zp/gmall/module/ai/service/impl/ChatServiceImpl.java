package com.zp.gmall.module.ai.service.impl;

import com.zp.gmall.module.ai.service.IChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;


@Service
public class ChatServiceImpl implements IChatService {


    private final ChatClient ollamaChatClient;


    private final ChatClient dashScopeChatClient;

    public ChatServiceImpl(@Qualifier("ollamaChatClient") ChatClient ollamaChatClient,
                           @Qualifier("dashScopeChatClient") ChatClient dashScopeChatClient) {
        this.ollamaChatClient = ollamaChatClient;
        this.dashScopeChatClient = dashScopeChatClient;
    }


    /**
     * Ollama 普通对话
     */
    @Override
    public String chat(String prompt) {
        return ollamaChatClient
                .prompt()
                .user(prompt)
                .call()
                .content();
    }

    /**
     * DashScope 普通对话
     */
    @Override
    public String dashChat(String prompt) {
        return dashScopeChatClient
                .prompt()
                .user(prompt)
                .call()
                .content();
    }

    /**
     * Ollama 历史消息对话
     */
    @Override
    public String chatHistory(List<Message> history) {
        Prompt prompt = new Prompt(history);
        ChatClient.CallResponseSpec response = ollamaChatClient.prompt(prompt).call();
        return response.content();
    }

    /**
     * Ollama 流式输出
     */
    @Override
    public Flux<String> stream(String prompt) {
        return ollamaChatClient.prompt().user(prompt).stream().content();
    }
}
