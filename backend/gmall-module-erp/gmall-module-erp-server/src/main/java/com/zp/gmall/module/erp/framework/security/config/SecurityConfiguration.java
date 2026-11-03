package com.zp.gmall.module.erp.framework.security.config;

import com.zp.gmall.framework.security.config.AuthorizeRequestsCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import java.util.List;

/**
 * Erp 模块安全配置，提供 {@link AuthorizeRequestsCustomizer} Bean。
 * 缺少该 Bean 会导致 GmallWebSecurityConfigurerAdapter 的
 * {@code List<AuthorizeRequestsCustomizer>} 注入失败，应用无法启动。
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-11-02
 */
@Configuration(proxyBeanMethods = false, value = "erpSecurityConfiguration")
public class SecurityConfiguration {

    @Bean
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {
            @Override
            public List<String> permitAllUrls() {
                return List.of(
                        // swagger / springdoc
                        "/v3/api-docs/**",
                        "/webjars/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/doc.html",
                        // actuator
                        "/actuator",
                        "/actuator/**"
                );
            }

            @Override
            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
                // 预留：非 permitAll 的授权规则
            }
        };
    }
}
