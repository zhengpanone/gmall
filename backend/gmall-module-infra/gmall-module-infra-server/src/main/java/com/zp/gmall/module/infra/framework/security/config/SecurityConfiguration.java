package com.zp.gmall.module.infra.framework.security.config;

import com.zp.gmall.framework.security.config.AuthorizeRequestsCustomizer;
import com.zp.gmall.module.infra.constant.ApiConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import java.util.List;

/**
 * Infra module security configuration.
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-13
 */
@Configuration(proxyBeanMethods = false, value = "infraSecurityConfiguration")
public class SecurityConfiguration {

    @Bean
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {
            @Override
            public List<String> permitAllUrls() {
                return List.of(
                        "/**",
                        "/v3/api-docs/**",
                        "/webjars/**",
                        "/swagger-ui/**",
                        "/druid/**",
                        "/actuator",
                        "/actuator/**",
                        ApiConstants.PREFIX + "/**"
                );
            }

            @Override
            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
                // Reserved for non-permitAll authorization rules.
            }
        };
    }
}
