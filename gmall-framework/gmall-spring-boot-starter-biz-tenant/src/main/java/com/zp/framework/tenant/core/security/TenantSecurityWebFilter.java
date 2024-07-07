package com.zp.framework.tenant.core.security;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.zp.framework.common.exception.enums.GlobalErrorCodeConstants;
import com.zp.framework.common.pojo.Result;
import com.zp.framework.common.util.servlet.ServletUtils;
import com.zp.framework.security.core.LoginUser;
import com.zp.framework.security.core.util.SecurityFrameworkUtils;
import com.zp.framework.tenant.config.TenantProperties;
import com.zp.framework.tenant.core.context.TenantContextHolder;
import com.zp.framework.tenant.core.service.TenantFrameworkService;
import com.zp.framework.web.config.WebProperties;
import com.zp.framework.web.core.filter.ApiRequestFilter;
import com.zp.framework.web.core.handler.GlobalExceptionHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.util.Objects;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 14:37
 * Version : v1.0.0
 * Description: 多租户 Security Web 过滤器
 * Filter对web服务器管理的所有资源进行拦截，例如实现URL级别的权限访问控制、过滤敏感词汇等
 * 1. 如果是登陆的用户，校验是否有权限访问该租户，避免越权问题。
 * 2. 如果请求未带租户的编号，检查是否是忽略的 URL，否则也不允许访问。
 * 3. 校验租户是合法，例如说被禁用、到期
 */
@Slf4j
public class TenantSecurityWebFilter extends ApiRequestFilter {

    private final TenantProperties tenantProperties;
    private final AntPathMatcher pathMatcher;

    private final GlobalExceptionHandler globalExceptionHandler;
    private final TenantFrameworkService tenantFrameworkService;

    public TenantSecurityWebFilter(TenantProperties tenantProperties, WebProperties webProperties,
                                   GlobalExceptionHandler globalExceptionHandler,
                                   TenantFrameworkService tenantFrameworkService) {
        super(webProperties);
        this.tenantProperties = tenantProperties;
        this.pathMatcher = new AntPathMatcher();
        this.globalExceptionHandler = globalExceptionHandler;
        this.tenantFrameworkService = tenantFrameworkService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tenantId = TenantContextHolder.getTenantId();
        // 1. 登陆的用户，校验是否有权限访问该租户，避免越权问题。
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();

        if (Objects.nonNull(loginUser)) {
            // 如果获取不到租户编号，则尝试使用登陆用户的租户编号
            if (StrUtil.isBlank(tenantId)) {
                tenantId = loginUser.getTenantId();
                TenantContextHolder.setTenantId(tenantId);

            }// 如果传递了租户编号，则进行比对租户编号，避免越权问题
            else if (!Objects.equals(loginUser.getTenantId(), TenantContextHolder.getTenantId())) {
                log.error("[doFilterInternal][租户({}) User({}/{}) 越权访问租户({}) URL({}/{})]",
                        loginUser.getTenantId(), loginUser.getId(), loginUser.getUserType(),
                        TenantContextHolder.getTenantId(), request.getRequestURI(), request.getMethod());
                ServletUtils.writeJSON(response, Result.failed(GlobalErrorCodeConstants.FORBIDDEN.code(), "您无权访问该租户的数据"));
                return;
            }
        }
        // 如果非允许忽略租户的 URL，则校验租户是否合法
        if (!isIgnoreUrl(request)) {
            // 2. 如果请求未带租户的编号，不允许访问。
            if (tenantId == null) {
                log.error("[doFilterInternal][URL({}/{}) 未传递租户编号]", request.getRequestURI(), request.getMethod());
                ServletUtils.writeJSON(response, Result.failed(GlobalErrorCodeConstants.BAD_REQUEST.code(), "请求的租户标识未传递，请进行排查"));
                return;
            }
            // 3. 校验租户是合法，例如说被禁用、到期
            try {
                tenantFrameworkService.validTenant(tenantId);
            } catch (Throwable e) {
                Result<?> result = globalExceptionHandler.allExceptionHandler(request, e);
                ServletUtils.writeJSON(response, result);
                return;
            }
        } else { // 如果是允许忽略租户的 URL，若未传递租户编号，则默认忽略租户编号，避免报错
            if (StrUtil.isBlank(tenantId)) {
                TenantContextHolder.setIgnore(true);
            }
        }
        // 继续过滤
        filterChain.doFilter(request, response);

    }

    private boolean isIgnoreUrl(HttpServletRequest request) {
        // 快速匹配，保证性能
        if (CollUtil.contains(tenantProperties.getIgnoreUrls(), request.getRequestURI())) {
            return true;
        }
        // 逐个 Ant 路径匹配
        for (String url : tenantProperties.getIgnoreUrls()) {
            if (pathMatcher.match(url, request.getRequestURI())) {
                return true;
            }
        }
        return false;
    }
}
