package com.zp.gmall.framework.validation.config;

import com.zp.gmall.framework.validation.handler.GlobalValidationExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

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
@AutoConfiguration
@EnableConfigurationProperties(ValidationProperties.class)
public class ValidationAutoConfiguration {

    @Bean
    @ConditionalOnClass(name = "org.springframework.web.bind.annotation.RestControllerAdvice")
    @ConditionalOnMissingBean
    public GlobalValidationExceptionHandler globalValidationExceptionHandler() {
        return new GlobalValidationExceptionHandler();
    }
}
