package com.zp.gmall.module.ai;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestOllama {

    @Test
    public void testOllama(@Autowired OllamaChatModel ollamaChatModel) {
        ChatResponse response = ollamaChatModel.call(new Prompt("你是谁",
                OllamaChatOptions
                        .builder()
                        .disableThinking() // 关闭思考模式
                        // 也可以再配置文件中配置：spring.ai.ollama.thinking=false
                        .build()));

        System.out.println(response.getResult().getOutput().getText());
    }
}
