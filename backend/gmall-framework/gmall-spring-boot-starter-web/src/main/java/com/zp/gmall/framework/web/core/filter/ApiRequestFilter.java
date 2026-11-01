package com.zp.gmall.framework.web.core.filter;

import cn.hutool.core.util.StrUtil;
import com.zp.gmall.framework.web.config.WebProperties;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * Description: API 请求过滤器
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-13
 */
@RequiredArgsConstructor
public abstract class ApiRequestFilter extends OncePerRequestFilter {

    protected final WebProperties webProperties;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // 只过滤 API 请求
        String apiUri = request.getRequestURI().substring(request.getContextPath().length());
        return StrUtil.startWithAny(apiUri, webProperties.getAdminApi().getPrefix(), webProperties.getAppApi().getPrefix());
    }
}
