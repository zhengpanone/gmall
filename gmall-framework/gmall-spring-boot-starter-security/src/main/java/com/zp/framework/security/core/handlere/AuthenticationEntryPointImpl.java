package com.zp.framework.security.core.handlere;

import com.zp.framework.common.exception.enums.GlobalErrorCodeConstants;
import com.zp.framework.common.pojo.Result;
import com.zp.framework.common.util.servlet.ServletUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

import static com.zp.framework.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;


/**
 * Author : zhengpanone
 * Date : 2024/2/2 15:25
 * Version : v1.0.0
 * Description:
 * 访问一个需要认证的 URL 资源，但是此时自己尚未认证（登录）的情况下，返回 {@link GlobalErrorCodeConstants#UNAUTHORIZED} 错误码，从而使前端重定向到登录页
 * 补充：Spring Security 通过 {@link ExceptionTranslationFilter#sendStartAuthentication(HttpServletRequest, HttpServletResponse, FilterChain, AuthenticationException)} 方法，调用当前类
 */
@Slf4j
@SuppressWarnings("JavadocReference")
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        log.debug("[commence] [访问 URL({})时，没有登录]", request.getRequestURL(), authException);
        ServletUtils.writeJSON(response, Result.failed(UNAUTHORIZED));
    }
}
