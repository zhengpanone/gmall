package com.zp.framework.security.core.util;

import com.zp.framework.security.core.LoginUser;
import jakarta.annotation.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Author : zhengpanone
 * Date : 2024/1/10 14:20
 * Version : v1.0.0
 * Description: 安全服务工具类
 */
public class SecurityFrameworkUtils {
    /**
     * 获得当前认证信息
     *
     * @return 认证信息
     */
    public static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        return context.getAuthentication();
    }

    @Nullable
    public static LoginUser getLoginUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getPrincipal() instanceof LoginUser ? (LoginUser) authentication.getPrincipal() : null;
    }
    /**
     * 获得当前用户的编号，从上下文中
     *
     * @return 用户编号
     */
    @Nullable
    public static Long getLoginUserId() {
        LoginUser loginUser = getLoginUser();
        return loginUser!=null ? loginUser.getId() : null;
    }
}
