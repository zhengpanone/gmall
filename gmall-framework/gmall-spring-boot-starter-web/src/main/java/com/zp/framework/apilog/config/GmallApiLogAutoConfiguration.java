package com.zp.framework.apilog.config;

import com.zp.framework.apilog.core.filter.ApiAccessLogFilter;
import com.zp.framework.apilog.core.interceptor.ApiAccessLogInterceptor;
import com.zp.framework.apilog.core.service.ApiAccessLogFrameworkService;
import com.zp.framework.apilog.core.service.ApiErrorLogFrameworkService;
import com.zp.framework.apilog.core.service.impl.ApiAccessLogFrameworkServiceImpl;
import com.zp.framework.apilog.core.service.impl.ApiErrorLogFrameworkServiceImpl;
import com.zp.framework.common.enums.WebFilterOrderEnum;
import com.zp.framework.web.config.GmallWebAutoConfiguration;
import com.zp.framework.web.config.WebProperties;
import com.zp.module.infra.api.logger.ApiAccessLogApi;
import com.zp.module.infra.api.logger.ApiErrorLogApi;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author : zhengpanone
 * Date : 2024/1/9 17:15
 * Version : v1.0.0
 */
@AutoConfiguration(after = GmallWebAutoConfiguration.class)
public class GmallApiLogAutoConfiguration implements WebMvcConfigurer {
    @Bean
    public ApiAccessLogFrameworkService apiAccessLogFrameworkService(ApiAccessLogApi apiAccessLogApi) {
        return new ApiAccessLogFrameworkServiceImpl(apiAccessLogApi);
    }

    @Bean
    public ApiErrorLogFrameworkService apiErrorLogFrameworkService(ApiErrorLogApi apiErrorLogApi) {
        return new ApiErrorLogFrameworkServiceImpl(apiErrorLogApi);
    }

    /**
     * 创建 ApiAccessLogFilter Bean，记录 API 请求日志
     */
    @Bean
    @ConditionalOnProperty(prefix = "gmall.access-log", value = "enable", matchIfMissing = true)
    // 允许使用 yudao.access-log.enable=false 禁用访问日志
    public FilterRegistrationBean<ApiAccessLogFilter> apiAccessLogFilter(WebProperties webProperties,
                                                                         @Value("${spring.application.name}") String applicationName,
                                                                         ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
        ApiAccessLogFilter filter = new ApiAccessLogFilter(webProperties, applicationName, apiAccessLogFrameworkService);
        return createFilterBean(filter, WebFilterOrderEnum.API_ACCESS_LOG_FILTER);
    }

    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiAccessLogInterceptor());
    }
}
