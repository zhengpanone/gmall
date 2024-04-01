package com.zp.framework.security.core.handlere;

import com.zp.framework.common.pojo.Result;
import com.zp.framework.common.utils.servlet.ServletUtils;
import com.zp.framework.security.core.util.SecurityFrameworkUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

import static com.zp.framework.common.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;

/**
 * Author : zhengpanone
 * Date : 2024/2/2 15:51
 * Version : v1.0.0
 * Description: TODO
 * 访问一个需要认证的 URL 资源，已经认证（登录）但是没有权限的情况下，返回 {@link GlobalErrorCodeConstants#FORBIDDEN} 错误码。
 * 补充：Spring Security 通过 {@link ExceptionTranslationFilter#handleAccessDeniedException(HttpServletRequest, HttpServletResponse, FilterChain, AccessDeniedException)} 方法，调用当前类
 */
@Slf4j
@SuppressWarnings("JavadocReference")
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 打印 warn 的原因是，不定期合并 warn，看看有没恶意破坏
        log.warn("[commence][访问 URL({}) 时，用户({}) 权限不够]", request.getRequestURI(),
                SecurityFrameworkUtils.getLoginUserId(), accessDeniedException);
        // 返回 403
        ServletUtils.writeJSON(response, Result.failed(FORBIDDEN));
    }
}
