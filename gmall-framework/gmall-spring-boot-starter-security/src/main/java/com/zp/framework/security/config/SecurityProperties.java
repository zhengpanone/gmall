package com.zp.framework.security.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2024/1/9 15:04
 * Version : v1.0.0
 
 */
@ConfigurationProperties(prefix = "gmall.security")
@Validated
@Data
public class SecurityProperties {
    /**
     * HTTP请求时，访问令牌的请求Header
     */
    @NotEmpty(message = "Token Header不能为空")
    private String tokenHeader = "Authorization";

    /**
     * HTTP 请求时，访问令牌的请求参数
     *
     * 初始目的：解决 WebSocket 无法通过 header 传参，只能通过 token 参数拼接
     */
    @NotEmpty(message = "Token Parameter 不能为空")
    private String tokenParameter = "token";

    /**
     * mock模式的开关
     */
    @NotNull(message = "mock模式的开关不能为空")
    private Boolean mockEnable = false;
    /**
     * mock模式的秘钥
     * 一定要配置秘钥，保证安全性
     */
    @NotEmpty(message = "mock模式的秘钥不能为空")
    private String mockSecret = "test";
    /**
     * 免登录的URL列表
     */
    private List<String> permitAllUrls = Collections.emptyList();
    /**
     * PasswordEncoder加密复杂度，越高开销越大
     */
    private Integer passwordEncoderLength = 4;
}
