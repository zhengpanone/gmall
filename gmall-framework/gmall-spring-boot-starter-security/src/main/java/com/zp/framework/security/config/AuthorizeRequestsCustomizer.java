package com.zp.framework.security.config;

import com.zp.framework.web.config.WebProperties;
import jakarta.annotation.Resource;
import org.springframework.core.Ordered;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

/**
 * Author : zhengpanone
 * Date : 2023/11/14 20:59
 * Version : v1.0.0
 * Description: 自定义的URL的安全配置
 * 目的：每个Maven Module可以自定义规则
 */
public abstract class AuthorizeRequestsCustomizer implements Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>, Ordered {
    @Resource
    private WebProperties webProperties;

    protected String buildAdminApi(String url) {
        return webProperties.getAdminApi().getPrefix() + url;
    }

    protected String buildAppApi(String url) {
        return webProperties.getAppApi().getPrefix() + url;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
