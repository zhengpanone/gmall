package com.zp.gmall.module.ai.utils;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class OllamaBenchmarkUtil {

    /**
     * 执行一次多模态调用，返回耗时和输出
     */
    public record CallResult(
            long firstTokenMs,
            long totalMs,
            long outputTokens,
            double tokensPerSecond,
            String text
    ) {
    }

    public CallResult callWithMetrics(
            OllamaChatModel chatModel,
            String prompt,
            Resource image,
            String model
    ) {
        var userMessage = UserMessage.builder()
                .text(prompt)
                .media(new Media(MimeTypeUtils.IMAGE_PNG, image))
                .build();

        var options = OllamaChatOptions.builder()
                .model(model)
                .disableThinking()
                .keepAlive("5m")
                .temperature(0.2)
                .build();

        // 流式拿首包时间
        long start = System.currentTimeMillis();
        final long[] firstTokenMs = {-1};
        StringBuilder sb = new StringBuilder();

        chatModel.stream(new Prompt(userMessage, options))
                .toIterable()
                .forEach(chunk -> {
                    chunk.getResult();
                    String t = chunk.getResult().getOutput().getText();
                    assert t != null;
                    if (!t.isEmpty()) {
                        if (firstTokenMs[0] < 0) {
                            firstTokenMs[0] = System.currentTimeMillis() - start;
                        }
                        sb.append(t);
                    }
                });

        long totalMs = System.currentTimeMillis() - start;
        long outputTokens = estimateTokens(sb.toString());
        double tps = outputTokens * 1000.0 / Math.max(totalMs, 1);

        return new CallResult(
                firstTokenMs[0],
                totalMs,
                outputTokens,
                Math.round(tps * 100.0) / 100.0,
                sb.toString()
        );
    }

    /**
     * 简单 token 估算（中文按字符，英文按空格分词，够压测用）
     */
    private long estimateTokens(String text) {
        if (text == null || text.isEmpty()) return 0;
        // 中文字符数 + 英文单词数
        long chinese = text.chars().filter(c -> c >= 0x4E00 && c <= 0x9FA5).count();
        long words = text.replaceAll("[\\u4e00-\\u9fa5]", " ")
                .trim().split("\\s+").length;
        return chinese + words;
    }

    /**
     * 执行 ollama ps，返回原始输出
     */
    public String ollamaPs() {
        try {
            Process p = new ProcessBuilder("ollama", "ps").start();
            try (var reader = new BufferedReader(
                    new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        } catch (Exception e) {
            return "ollama ps failed: " + e.getMessage();
        }
    }
}