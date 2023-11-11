package com.zp.framework.web.core.filter;

import cn.hutool.core.util.StrUtil;
import com.zp.framework.web.config.WebProperties;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Author : zhengpanone
 * Date : 2023/11/14 22:07
 * Version : v1.0.0
 * Description: 过滤 /admin-api、/app-api等API请求的过滤器
 */
@RequiredArgsConstructor
public abstract class ApiRequestFilter extends OncePerRequestFilter {
    protected final WebProperties webProperties;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // 只过滤API请求的地址
        return !StrUtil.startWithAny(request.getRequestURI(),
                webProperties.getAdminApi().getPrefix(),
                webProperties.getAppApi().getPrefix());
    }
}
