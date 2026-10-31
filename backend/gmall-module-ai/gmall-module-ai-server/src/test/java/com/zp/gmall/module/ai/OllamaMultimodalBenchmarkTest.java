package com.zp.gmall.module.ai;

import com.zp.gmall.module.ai.utils.OllamaBenchmarkUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;

@SpringBootTest
@DisplayName("Ollama Qwen3-VL 多模态压测")
class OllamaMultimodalBenchmarkTest {

    @Autowired
    OllamaChatModel ollamaChatModel;

    @Autowired
    OllamaBenchmarkUtil benchmark;

    private static final String MODEL = "qwen3-vl:2b";

    @Test
    @DisplayName("单图压测：架构图 → 打印 tok/s 和 ollama ps")
    void benchmarkSingleImage() {
        var image = new ClassPathResource("files/images/arch.png");

        System.out.println("===== 请求前 ollama ps =====");
        System.out.println(benchmark.ollamaPs());

        var result = benchmark.callWithMetrics(
                ollamaChatModel,
                "用中文简要描述这张图，不超过100字",
                image,
                MODEL
        );

        System.out.println("===== 模型输出 =====");
        System.out.println(result.text());
        System.out.println("===== 性能指标 =====");
        System.out.printf("首包延迟: %d ms%n", result.firstTokenMs());
        System.out.printf("总耗时: %d ms%n", result.totalMs());
        System.out.printf("输出 tokens(估算): %d%n", result.outputTokens());
        System.out.printf("吞吐: %.2f tok/s%n", result.tokensPerSecond());

        System.out.println("===== 请求后 ollama ps =====");
        System.out.println(benchmark.ollamaPs());

        Assertions.assertTrue(result.tokensPerSecond() > 0);
    }

    @Test
    @DisplayName("循环压测：连续 5 次，观察 GPU 是否稳定")
    void benchmarkLoop() {
        var image = new ClassPathResource("files/images/arch.png");

        for (int i = 1; i <= 5; i++) {
            System.out.println("===== 第 " + i + " 次请求 =====");
            var result = benchmark.callWithMetrics(
                    ollamaChatModel,
                    "这张图里有哪些组件？只列名字",
                    image,
                    MODEL
            );
            System.out.printf("耗时=%dms, 首包=%dms, %.2f tok/s%n",
                    result.totalMs(), result.firstTokenMs(), result.tokensPerSecond());
            Assertions.assertTrue(result.tokensPerSecond() > 0);
        }

        System.out.println("===== 压测结束 ollama ps =====");
        System.out.println(benchmark.ollamaPs());
    }

    @Test
    @DisplayName("不同图片尺寸对比：小图 vs 大图")
    void benchmarkImageSize() {
        var small = new ClassPathResource("files/images/arch_720p.png");
        var large = new ClassPathResource("files/images/arch_2k.png");

        for (var entry : Map.of("720p", small, "2K", large).entrySet()) {
            System.out.println("===== 图片尺寸: " + entry.getKey() + " =====");
            var result = benchmark.callWithMetrics(
                    ollamaChatModel,
                    "描述图片",
                    entry.getValue(),
                    MODEL
            );
            System.out.printf("首包=%dms, 总耗时=%dms, %.2f tok/s%n",
                    result.firstTokenMs(), result.totalMs(), result.tokensPerSecond());
        }

        System.out.println(benchmark.ollamaPs());
    }
}