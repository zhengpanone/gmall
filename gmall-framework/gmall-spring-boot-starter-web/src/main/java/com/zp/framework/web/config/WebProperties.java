package com.zp.framework.web.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Author : zhengpanone
 * Date : 2023/11/14 21:04
 * Version : v1.0.0
 * Description:
 */

@ConfigurationProperties(prefix = "gmall.web")
@Validated
@Data
public class WebProperties {
    @NotNull(message = "APP API不能为空")
    private Api appApi = new Api("/app-api", "**.controller.app.**");
    @NotNull(message = "Admin API不能为空")
    private Api adminApi = new Api("/admin-api", "**.controller.admin.**");
    // @NotNull(message = "Admin UI不能为空")
    private UI adminUI;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Valid
    public static class Api {
        /**
         * API前缀、实现所有Controller提供的ResultFul API 的统一前缀
         * 意义：通过该前缀，避免 Swagger、Actuator 意外通过 Nginx 暴露出来给外部，带来安全性问题
         * 这样，Nginx 只需要配置转发到 /api/* 的所有接口即可。
         */
        @NotEmpty(message = "API前缀不能为空")
        private String prefix;
        @NotEmpty(message = "Controller所在的包不能为空")
        private String controller;
    }

    @Data
    @Valid
    public static class UI {
        /**
         * 访问地址
         */
        private String url;
    }
}
