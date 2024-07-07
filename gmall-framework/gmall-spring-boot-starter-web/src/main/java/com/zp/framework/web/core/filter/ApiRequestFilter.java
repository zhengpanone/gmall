package com.zp.framework.web.core.filter;

import cn.hutool.core.util.StrUtil;
import com.zp.framework.web.config.WebProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * Spring的OncePerRequestFilter类实际上是一个实现了Filter接口的抽象类。spring对Filter进行了一些封装处理，能够确保在一次请求只通过一次filter，
 * 而不需要重复执行，因为在执行完后会在request设置标识符。
 * 过滤 /admin-api、/app-api 等 API 请求的过滤器
 *
 * @author 芋道源码
 */
@RequiredArgsConstructor
public abstract class ApiRequestFilter extends OncePerRequestFilter {

    protected final WebProperties webProperties;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 只过滤 API 请求的地址
        return !StrUtil.startWithAny(request.getRequestURI(), webProperties.getAdminApi().getPrefix(),
                webProperties.getAppApi().getPrefix());
    }

}
