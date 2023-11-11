package com.zp.framework.web.util;

import cn.hutool.core.util.NumberUtil;
import com.zp.framework.common.enums.RpcConstants;
import com.zp.framework.common.enums.UserTypeEnum;
import com.zp.framework.common.pojo.Result;
import com.zp.framework.web.config.WebProperties;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Author : zhengpanone
 * Date : 2023/11/17 12:28
 * Version : v1.0.0
 * Description: 专属于web包的工具类
 */
public class WebFrameworkUtils {
    private static final String REQUEST_ATTRIBUTE_LOGIN_USER_ID = "login_user_id";
    private static final String REQUEST_ATTRIBUTE_LOGIN_USER_TYPE = "login_user_type";

    private static final String REQUEST_ATTRIBUTE_COMMON_RESULT = "common_result";

    public static final String HEADER_TENANT_ID = "tenant_id";

    private static WebProperties properties;

    public WebFrameworkUtils(WebProperties properties) {
        WebFrameworkUtils.properties = properties;
    }

    /**
     * 获得租户编号，从header中
     * 考虑到其它 framework 组件也会使用到租户编号，所以不得不放在 WebFrameworkUtils 统一提供
     *
     * @param request 请求
     * @return 租户编号
     */
    public static Long getTenantId(HttpServletRequest request) {
        String tenantId = request.getHeader(HEADER_TENANT_ID);
        return NumberUtil.isNumber(tenantId) ? Long.valueOf(tenantId) : null;
    }

    /**
     * 设置用户ID
     *
     * @param request 请求
     * @param userId  用户ID
     */
    public static void setLoginUserId(ServletRequest request, Long userId) {
        request.setAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_ID, userId);
    }

    /**
     * 设置用户类型
     *
     * @param request  请求
     * @param userType 用户类型
     */
    public static void setLoginUserType(ServletRequest request, Long userType) {
        request.setAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_TYPE, userType);
    }

    /**
     * 获得当前用户的编号，从请求中
     * 注意：该方法仅限于 framework 框架使用！！！
     *
     * @param request 请求
     * @return 用户编号
     */
    public static Long getLoginUserId(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        return (Long) request.getAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_ID);
    }

    /**
     * 获得当前用户的类型
     * 注意：该方法仅限于 web 相关的 framework 组件使用！！！
     *
     * @param request 请求
     * @return 用户编号
     */
    public static Integer getLoginUserType(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        // 1.优先从Attribute中获取
        Integer userType = (Integer) request.getAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_TYPE);
        if (userType != null) {
            return userType;
        }
        // 2.其次，基于URL前缀的约定
        if (request.getServletPath().startsWith(properties.getAdminApi().getPrefix())) {
            return UserTypeEnum.ADMIN.getValue();
        }
        if (request.getServletPath().startsWith(properties.getAppApi().getPrefix())) {
            return UserTypeEnum.MEMBER.getValue();
        }
        return null;
    }

    public static Integer getLoginUserType() {
        HttpServletRequest request = getRequest();
        return getLoginUserType(request);
    }

    public static Long getLoginUserId() {
        HttpServletRequest request = getRequest();
        return getLoginUserId(request);
    }

    public static void setResult(ServletRequest request, Result<?> result) {
        request.setAttribute(REQUEST_ATTRIBUTE_COMMON_RESULT, result);
    }

    public static Result<?> getResult() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes servletRequestAttributes)) {
            return null;
        }
        return (Result<?>) servletRequestAttributes.getRequest();
    }


    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes servletRequestAttributes)) {
            return null;
        }
        return servletRequestAttributes.getRequest();

    }

    /**
     * 判断是否为 RPC 请求
     *
     * @param request 请求
     * @return 是否为 RPC 请求
     */
    public static boolean isRpcRequest(HttpServletRequest request) {
        return request.getRequestURI().startsWith(RpcConstants.RPC_API_PREFIX);
    }

    /**
     * 判断是否为 RPC 请求
     * 约定大于配置，只要以 Api 结尾，都认为是 RPC 接口
     *
     * @param className 类名
     * @return 是否为 RPC 请求
     */
    public static boolean isRpcRequest(String className) {
        return className.endsWith("Api");
    }


}