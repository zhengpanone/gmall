package com.zp.gmall.framework.web.util;

import com.zp.gmall.framework.common.enums.UserTypeEnum;
import com.zp.gmall.framework.web.config.WebProperties;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.zp.gmall.framework.common.constant.GlobalConstant.*;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.framework.web.util
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-07
 */
public class WebFrameworkUtils {

    private static WebProperties properties;

    public WebFrameworkUtils(WebProperties webProperties) {
        WebFrameworkUtils.properties = webProperties;
    }

    /**
     * 获得租户编号，从header中
     *
     * @param request 请求
     * @return 租户编号
     */
    public static String getTenantId(HttpServletRequest request) {
        return request.getHeader(HEADER_TENANT_ID);
    }

    public static String getLoginUserId(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        return (String) request.getAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_ID);
    }

    public static String getLoginUserType(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        // 优先从 Attribute中获取
        String userType = (String) request.getAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_TYPE);
        if (userType != null) {
            return userType;
        }
        // 其次基于URL前缀的约定
        if (request.getServletPath().startsWith(properties.getAdminApi().getPrefix())) {
            return UserTypeEnum.ADMIN.getValue();
        }
        if (request.getServletPath().startsWith(properties.getAppApi().getPrefix())) {
            return UserTypeEnum.MEMBER.getValue();
        }
        return null;
    }

    public static String getLoginUserType() {
        HttpServletRequest request = getRequest();
        return getLoginUserType(request);
    }

    public static void setLoginUserId(ServletRequest request, String loginUserId) {
        request.setAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_ID, loginUserId);
    }

    public static void setLoginUserType(ServletRequest request, String userType) {
        request.setAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_TYPE, userType);
    }

    @SuppressWarnings("PatternVariableCanBeUsed")
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes servletRequestAttributes)) {
            return null;
        }
        return servletRequestAttributes.getRequest();

    }

}
