package com.zp.gmall.module.ai.service;

import org.springframework.ai.chat.messages.Message;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IChatService {

    String chat(String prompt);


    String dashChat(String prompt);

    /**
     * 多轮对话（上下文记忆）
     * @param history
     * @return
     */
    String chat(List<Message> history);

    Flux<String> stream(String prompt);
}
