package com.zp.framework.apilog.core.filter;

import com.zp.framework.apilog.core.service.ApiAccessLogFrameworkService;
import com.zp.framework.web.config.WebProperties;
import com.zp.framework.web.core.filter.ApiRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Author : zhengpanone
 * Date : 2023/11/14 22:06
 * Version : v1.0.0
 * Description: API访问日志Filter
 */
public class ApiAccessLogFilter extends ApiRequestFilter {
    private final String applicationName;
    private final ApiAccessLogFrameworkService apiAccessLogFrameworkService;

    public ApiAccessLogFilter(WebProperties webProperties, String applicationName, ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
        super(webProperties);
        this.applicationName = applicationName;
        this.apiAccessLogFrameworkService = apiAccessLogFrameworkService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
