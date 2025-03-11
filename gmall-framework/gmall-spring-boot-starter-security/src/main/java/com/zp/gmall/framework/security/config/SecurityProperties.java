package com.zp.gmall.framework.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/4/2 14:10
 * Version : v1.0.0
 * Description:
 */
@Data
@ConfigurationProperties(prefix = "gmall.security")
public class SecurityProperties {
    /**
     * 免登录的 URL 列表
     */
    private List<String> permitAllUrls = Collections.emptyList();

    /**
     * PasswordEncoder 加密复杂度，越高开销越大
     */
    private Integer passwordEncoderLength = 4;
}
