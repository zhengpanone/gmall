package com.zp.gmall.module.system.enums.permission;

import com.zp.gmall.framework.common.util.object.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色标识枚举
 */
@Getter
@AllArgsConstructor
public enum RoleCodeEnum {

    SUPER_ADMIN("super_admin", "超级管理员"),
    // 三权
    SYSTEM_ADMIN("system_admin", "系统管理员"),
    SECURITY_ADMIN("security_admin", "安全管理员"),
    AUDIT_ADMIN("audit_admin", "审计管理员"),

    AUTH_ADMIN("auth_admin", "权限管理员"),
    USER_ADMIN("user_admin", "用户管理员"),
    TENANT_ADMIN("tenant_admin", "租户管理员"),
    CRM_ADMIN("crm_admin", "CRM 管理员"); // CRM 系统专用
    ;

    /**
     * 角色编码
     */
    private final String code;
    /**
     * 名字
     */
    private final String name;

    public static boolean isSuperAdmin(String code) {
        return ObjectUtils.equalsAny(code, SUPER_ADMIN.getCode());
    }

    public static boolean isSystemAdmin(String code) {
        return ObjectUtils.equalsAny(code, SYSTEM_ADMIN.getCode());
    }

}
