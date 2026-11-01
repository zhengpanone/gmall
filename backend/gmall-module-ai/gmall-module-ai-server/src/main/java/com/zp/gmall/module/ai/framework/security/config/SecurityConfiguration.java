package com.zp.gmall.module.ai.framework.security.config;

import com.zp.gmall.framework.security.config.AuthorizeRequestsCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import java.util.List;

@Configuration(proxyBeanMethods = false, value = "aiSecurityConfiguration")
public class SecurityConfiguration {
    @Bean
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {
            @Override
            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizationManagerRequestMatcherRegistry) {

            }

            @Override
            public List<String> permitAllUrls() {
                return List.of(
                        buildAdminApi("/ai/**")
                );
            }
        };
    }
}
