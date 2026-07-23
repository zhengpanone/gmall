package com.zp.gmall.module.ai.framework.ai.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Spring AI LLM 配置
 * <p>
 * 支持：
 * 1. Spring AI Alibaba + DashScope
 * 2. Spring AI + Ollama
 */
@Configuration
public class SaaLLMConfig {
    /**
     * ================================
     * DashScope
     * ================================
     */

    @Value("${spring.ai.dashscope.api-key}")
    private String apiKey;

    /**
     * DashScope API
     */
    @Bean
    public DashScopeApi dashScopeApi() {
        return DashScopeApi.builder()
                .apiKey(apiKey)
                .build();
    }


    /**
     * 千问Plus
     *
     * @param dashScopeApi
     * @return
     */
    @Bean("dashScopeChatModel")
    public DashScopeChatModel dashScopeChatModel(DashScopeApi dashScopeApi) {
        return DashScopeChatModel.builder()
                .dashScopeApi(dashScopeApi)
                .defaultOptions(
                        DashScopeChatOptions.builder()
                                .model("qwen-plus")
                                .temperature(0.7)
                                .build()
                ).build();
    }

    /**
     * 千问Plus
     *
     * @param
     * @return
     */
    @Bean("dashScopeChatClient")
    public ChatClient chatClient(@Qualifier("dashScopeChatModel") ChatModel dashScopeChatModel) {
        return ChatClient.builder(dashScopeChatModel)
                .defaultSystem("你是由阿里云通义千问驱动的智能助手。")
//                .defaultOptions(
//                        DashScopeChatOptions.builder()
//                                .model("qwen-plus")// 指定模型 ID
//                                .temperature(0.7)
//                                .build()
//                )
                .build();
    }

    /**
     * Ollama
     *
     * @param ollamaChatModel
     * @return
     */
    @Bean("ollamaChatClient")
    public ChatClient ollamaChatClient(@Qualifier("ollamaChatModel") ChatModel ollamaChatModel) {
        return ChatClient.builder(ollamaChatModel)
                .defaultSystem("你是一个运行在本地 Ollama 上的智能助手。")
                .build();
    }


    @Bean("ollamaReactAgent")
    public ReactAgent ollamaReactAgent(@Qualifier("ollamaChatModel") ChatModel ollamaChatModel) {
        return ReactAgent.builder()
                .name("ollama_agent")
                .model(ollamaChatModel)
                .build();
    }

    @Bean("dashScopeReactAgent")
    @Primary
    public ReactAgent dashScopeReactAgent(@Qualifier("dashScopeChatModel") ChatModel dashScopeChatModel) {
        return ReactAgent.builder()
                .name("dashScope_agent")
                .model(dashScopeChatModel)
                .build();
    }

}
