package com.zp.framework.tenant.core.web;

import cn.hutool.core.util.StrUtil;
import com.zp.framework.tenant.core.context.TenantContextHolder;
import com.zp.framework.web.core.util.WebFrameworkUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 15:25
 * Version : v1.0.0
 * Description: 多租户Context web过滤器
 * 将请求 Header 中的 tenant-id 解析出来，添加到 {@link TenantContextHolder} 中，这样后续的 DB 等操作，可以获得到租户编号。
 */
public class TenantContextWebFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 设置租户ID
        String tenantId = WebFrameworkUtils.getTenantId(request);
        if (StrUtil.isNotBlank(tenantId)) {
            TenantContextHolder.setTenantId(tenantId);
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            // 清理
            TenantContextHolder.clear();
        }
    }
}
