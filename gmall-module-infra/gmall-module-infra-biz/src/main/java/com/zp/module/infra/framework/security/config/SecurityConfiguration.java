package com.zp.module.infra.framework.security.config;

import com.zp.framework.security.config.AuthorizeRequestsCustomizer;
import com.zp.module.infra.enums.ApiConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

/**
 * Author : zhengpanone
 * Date : 2024/5/19 01:18
 * Version : v1.0.0
 * Description: Infra模块的ySecurity配置
 */
@Configuration(proxyBeanMethods = false, value = "infraSecurityConfiguration")
public class SecurityConfiguration {

    @Value("${spring.boot.admin.context-path:''}")
    private String adminSeverContextPath;
    @Bean("infraAuthorizeRequestCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {

            @Override
            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
                // swagger ui接口文档
                registry.requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll();
                // // Spring Boot Actuator 的安全配置
                registry.requestMatchers(antMatcher("/actuator/**")).anonymous();

                // Druid 监控
                registry.requestMatchers("/druid/**").anonymous();

                // Spring Boot Admin Server 的安全配置
                registry.requestMatchers(antMatcher(adminSeverContextPath+"/**")).anonymous();
                // 文件读取
                registry.requestMatchers(buildAdminApi("/infra/file/*/get/**")).permitAll();

                // RPC 服务的安全配置
                registry.requestMatchers(ApiConstants.PREFIX + "/**").permitAll();

            }
        };
    }
}
