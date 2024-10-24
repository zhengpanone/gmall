package com.zp.framework.tenant.config;

import com.zp.framework.common.enums.WebFilterOrderEnum;
import com.zp.framework.tenant.core.security.TenantSecurityWebFilter;
import com.zp.framework.tenant.core.service.TenantFrameworkService;
import com.zp.framework.tenant.core.service.impl.TenantFrameworkServiceImpl;
import com.zp.framework.tenant.core.web.TenantContextWebFilter;
import com.zp.framework.web.config.WebProperties;
import com.zp.framework.web.core.handler.GlobalExceptionHandler;
import com.zp.module.system.api.tenant.TenantApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 12:52
 * Version : v1.0.0
 * Description: TODO
 */
@AutoConfiguration
// 允许使用 gmall.tenant.enable=false 禁用多租户
@ConditionalOnProperty(prefix = "gmall.tenant", value = "enable", matchIfMissing = true)
@EnableConfigurationProperties(TenantProperties.class)
public class GmallTenantAutoConfiguration {

    @Bean
    public TenantFrameworkService tenantFrameworkService(TenantApi tenantApi) {
        return new TenantFrameworkServiceImpl(tenantApi);
    }
    // TODO


    // ========== WEB ==========
    @Bean
    public FilterRegistrationBean<TenantContextWebFilter> tenantContextWebFilter() {
        FilterRegistrationBean<TenantContextWebFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TenantContextWebFilter());
        registrationBean.setOrder(WebFilterOrderEnum.TENANT_CONTEXT_FILTER);
        return registrationBean;
    }


    // ========== Security ==========
    @Bean
    public FilterRegistrationBean<TenantSecurityWebFilter> tenantSecurityWebFilter(TenantProperties tenantProperties,
                                                                                   WebProperties webProperties,
                                                                                   GlobalExceptionHandler globalExceptionHandler,
                                                                                   TenantFrameworkService tenantFrameworkService) {
        FilterRegistrationBean<TenantSecurityWebFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TenantSecurityWebFilter(tenantProperties, webProperties,
                globalExceptionHandler, tenantFrameworkService));
        registrationBean.setOrder(WebFilterOrderEnum.TENANT_SECURITY_FILTER);
        return registrationBean;
    }
}
