package com.zp.gmall.framework.security;

import com.zp.gmall.framework.security.config.SecurityProperties;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Author : zhengpanone
 * Date : 2025/4/2 14:14
 * Version : v1.0.0
 * Description:
 */
@AutoConfiguration
@EnableConfigurationProperties(SecurityProperties.class)
public class GmallSecurityAutoConfiguration {
    @Resource
    private SecurityProperties securityProperties;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(securityProperties.getPasswordEncoderLength());
    }
}
