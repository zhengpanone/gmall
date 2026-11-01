package com.zp.gmall.framework.validation.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.framework.validation.config
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-07
 */
@ConfigurationProperties(prefix = "gmall.validation")
@Data
public class ValidationProperties {

    /**
     * 是否启用 XSS 校验
     */
    private boolean xssEnabled = true;

    /**
     * 是否启用 SQL 注入校验
     */
    private boolean sqlInjectEnabled = true;
}
