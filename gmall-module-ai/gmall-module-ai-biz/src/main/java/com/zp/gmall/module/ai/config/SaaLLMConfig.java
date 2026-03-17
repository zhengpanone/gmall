package com.zp.gmall.module.ai.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeAgentApi;
import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : zhengpanone
 * Date : 2026/3/17 22:23
 * Version : v1.0.0
 * Description: Spring AI Alibaba LLM
 */
@Configuration
public class SaaLLMConfig {

    @Value("${spring.ai.dashscope.api-key}")
    private String apiKey;

    @Value("${spring.ai.dashscope.chat.options.model}")
    private String chatModel;

    @Bean
    public DashScopeApi dashScopeApi() {
        return DashScopeApi.builder().apiKey(apiKey).build();
    }

    @Bean
    public ChatModel chatModel(DashScopeApi dashScopeApi) {
        return DashScopeChatModel.builder().dashScopeApi(dashScopeApi)
                .defaultOptions(DashScopeChatOptions.builder().model(chatModel)
                        .temperature(0.7)
                        .build())
                .build();
    }

    @Bean
    public ReactAgent reactAgent(ChatModel chatModel) {
        return ReactAgent.builder()
                .name("my_agent")
                .model(chatModel)
                .build();
    }

}
