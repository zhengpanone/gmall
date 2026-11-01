package com.zp.gmall.module.ai;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TestOllama {

    // 不要注入 Spring AI 自动配置的 ChatClient.Builder：
    // 项目里同时存在 dashScopeChatModel 和 ollamaChatModel，ChatClientAutoConfiguration 需要一个唯一的
    // ChatModel，会报 NoUniqueBeanDefinitionException。
    // 要用 ChatClient 就直接用 SaaLLMConfig 里的命名 Bean：@Qualifier("ollamaChatClient")
    @Autowired
    private OllamaChatModel ollamaChatModel;


    @Test
    @DisplayName("测试Ollama")
    public void testOllama() {
        ChatResponse response = ollamaChatModel.call(new Prompt("你是谁",
                OllamaChatOptions
                        .builder()
                        .disableThinking() // 关闭思考模式
                        // 也可以再配置文件中配置：spring.ai.ollama.thinking=false
                        .build()));

        System.out.println(response.getResult().getOutput().getText());
    }

    @Test
    @DisplayName("测试图片理解")
    public void testImageUnderstand(@Autowired OllamaChatModel ollamaChatModel) {
        var imageResource = new ClassPathResource("files/images/arch.png");

        OllamaChatOptions ollamaOptions = OllamaChatOptions.builder().model("qwen3-vl:2b").build();
        Media media = new Media(MimeTypeUtils.IMAGE_PNG, imageResource);
        ChatResponse response = ollamaChatModel.call(
                new Prompt(UserMessage.builder()
                        .media(media)
                        .text("识别图片")
                        .build(),
                        ollamaOptions));
        System.out.println(response.getResult().getOutput().getText());
    }

    /**
     * ChatClient 写法
     */
    @Test
    void testVisionByChatClient() {
        var image = new ClassPathResource("files/images/arch.png");

        String result = ChatClient.builder(ollamaChatModel).build().prompt()
                .user(u -> u.text("这张图是什么架构？只回答关键点")
                        .media(MimeTypeUtils.IMAGE_PNG, image))
                .options(OllamaChatOptions.builder()
                        .model("qwen3-vl:2b")
                        .disableThinking()
                        .build())
                .call()
                .content();

        System.out.println(result);
    }


    /**
     *
     * 流式输出（适合前端打字机效果）
     */
    @DisplayName("流式输出")
    @Test
    void testVersionStream() {
        var imageResource = new ClassPathResource("files/images/arch.png");
        var prompt = new Prompt(
                UserMessage.builder()
                        .text("逐步解释这张图")
                        .media(new Media(MimeTypeUtils.IMAGE_PNG, imageResource))
                        .build(),
                OllamaChatOptions.builder()
                        .model("qwen3-vl:2b")
                        .disableThinking()
                        .build()
        );

        ollamaChatModel.stream(prompt)
                .toIterable()
                .forEach(chunk -> {
                    chunk.getResult();
                    String t = chunk.getResult().getOutput().getText();
                    System.out.println(t);
                });
    }

    /**
     * 用 base64 / byte[]（不依赖 classpath 文件）
     *
     * @throws IOException
     */
    @DisplayName("测试用 base64 / byte[] 上传图片")
    @Test
    void testImageByBytes() throws IOException {
        byte[] bytes = new ClassPathResource("files/images/arch.png")
                .getContentAsByteArray();

        var resource = new ByteArrayResource(bytes) {
            @Override
            public @Nullable String getFilename() {
                return super.getFilename();
            }
        };

        var media = new Media(MimeTypeUtils.IMAGE_PNG, resource);

        var userMessage = UserMessage.builder().text("描述这张图")
                .media(media).build();

        ChatResponse resp = ollamaChatModel.call(
                new Prompt(userMessage,
                        OllamaChatOptions.builder()
                                .model("qwen3-vl:2b")
                                .disableThinking()
                                .build())
        );
        System.out.println(resp.getResult().getOutput().getText());
    }

    /**
     * 模拟前端上传 MultipartFile
     *
     * @throws IOException
     */
    @DisplayName("模拟前端测试上传图片")
    @Test
    void testUploadImage() throws IOException {
        // 这里用 classpath 文件模拟上传
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "arch.png",
                MediaType.IMAGE_PNG_VALUE,
                new ClassPathResource("files/images/arch.png").getInputStream()
        );

        ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        Assertions.assertNotNull(file.getContentType());
        MimeType mime = MimeTypeUtils.parseMimeType(file.getContentType());

        ChatResponse resp = ollamaChatModel.call(
                new Prompt(
                        UserMessage.builder().text("描述上传图片")
                                .media(new Media(mime, resource)).build(),

                        OllamaChatOptions.builder()
                                .model("qwen3-vl:2b")
                                .disableThinking()
                                .build()
                )
        );
        System.out.println(resp.getResult().getOutput().getText());
    }

    /**
     * 断言更严谨的测试写法
     */
    @Test
    @DisplayName("测试多模态图像识别断言")
    void testMultimodalAssert() {
        // 注意：测试资源实际放在 src/test/resources/files/images/ 下
        var image = new ClassPathResource("files/images/arch.png");

        String text = ollamaChatModel.call(new Prompt(
                UserMessage.builder()
                        .text("只提取图片中的金额和商品名，没有就写无")
                        .media(new Media(MimeTypeUtils.IMAGE_PNG, image))
                        .build(),
                OllamaChatOptions.builder()
                        .model("qwen3-vl:2b")
                        .disableThinking()
                        .temperature(0.2)
                        .build()
        )).getResult().getOutput().getText();

        System.out.println(text);

        // 简单断言：返回里不能空，且最好包含“金额”或“商品”
        assertThat(text).isNotBlank();
    }
}
