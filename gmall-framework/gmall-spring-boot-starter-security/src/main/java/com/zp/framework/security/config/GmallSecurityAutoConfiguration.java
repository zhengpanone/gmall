package com.zp.framework.security.config;

import com.zp.framework.security.core.aop.PreAuthenticatedAspect;
import com.zp.framework.security.core.filter.TokenAuthenticationFilter;
import com.zp.framework.security.core.handlere.AccessDeniedHandlerImpl;
import com.zp.framework.security.core.handlere.AuthenticationEntryPointImpl;
import com.zp.framework.web.core.handler.GlobalExceptionHandler;
import com.zp.module.system.api.oauth2.OAuth2TokenApi;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Author : zhengpanone
 * Date : 2024/1/9 15:11
 * Version : v1.0.0
 * Description: Spring Security自动配置类，主要用于相关组件的配置
 * 注意，不能和 {@link GmallWebSecurityConfigurerAdapter} 用一个，原因是会导致初始化报错。
 * 参见 https://stackoverflow.com/questions/53847050/spring-boot-delegatebuilder-cannot-be-null-on-autowiring-authenticationmanager 文档。
 */
@AutoConfiguration
@EnableConfigurationProperties(SecurityProperties.class)
public class GmallSecurityAutoConfiguration {
    @Resource
    private SecurityProperties securityProperties;

    /**
     * 处理用户未登录拦截的切面的Bean
     */
    @Bean
    public PreAuthenticatedAspect preAuthenticatedAspect() {
        return new PreAuthenticatedAspect();
    }

    /**
     * 认证失败处理类
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    /**
     * 权限不够处理器
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    /**
     * Spring Security 加密器
     * 考虑到安全性，这里采用 BCryptPasswordEncoder 加密器
     *
     * @see <a href="http://stackabuse.com/password-encoding-with-spring-security/">Password Encoding with Spring Security</a>
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(securityProperties.getPasswordEncoderLength());
    }

    /**
     * Token 认证过滤器 Bean
     */
    @Bean
    public TokenAuthenticationFilter authenticationFilter(GlobalExceptionHandler globalExceptionHandler, OAuth2TokenApi oAuth2TokenApi) {
        return new TokenAuthenticationFilter(securityProperties, globalExceptionHandler, oAuth2TokenApi);
    }
}
