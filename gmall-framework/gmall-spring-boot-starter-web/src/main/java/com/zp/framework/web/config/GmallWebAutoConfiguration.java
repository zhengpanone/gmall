package com.zp.framework.web.config;

import cn.hutool.core.text.AntPathMatcher;
import com.zp.framework.apilog.core.service.ApiErrorLogFrameworkService;
import com.zp.framework.web.core.handler.GlobalExceptionHandler;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author : zhengpanone
 * Date : 2023/11/14 21:51
 * Version : v1.0.0
 * Description:
 */
@AutoConfiguration
@EnableConfigurationProperties(WebProperties.class)
public class GmallWebAutoConfiguration implements WebMvcConfigurer {
    @Resource
    private WebProperties webProperties;
    /**
     * 应用名
     */
    @Value("${spring.application.name")
    private String applicationName;


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurePathMatch(configurer, webProperties.getAdminApi());
        configurePathMatch(configurer, webProperties.getAppApi());
    }

    /**
     * 设置API前缀，仅仅匹配controller包下的
     *
     * @param configurer 配置
     * @param api        API配置
     */
    private void configurePathMatch(PathMatchConfigurer configurer, WebProperties.Api api) {
        AntPathMatcher antPathMatcher = new AntPathMatcher(".");
        configurer.addPathPrefix(api.getPrefix(), clazz -> clazz.isAnnotationPresent(RestController.class)
                && antPathMatcher.match(api.getController(), clazz.getPackage().getName())); // 仅仅匹配controller
    }

   /* @Bean
    public GlobalExceptionHandler globalExceptionHandler(ApiErrorLogFrameworkService apiErrorLogFrameworkService) {
        return new GlobalExceptionHandler(applicationName, apiErrorLogFrameworkService);
    }*/


}
