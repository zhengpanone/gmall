package com.zp.framework.tenant.core.context;

import com.zp.framework.common.enums.DocumentEnum;

import java.util.Objects;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 14:43
 * Version : v1.0.0
 * Description:  多租户上下文 Holder
 */
public class TenantContextHolder {
    /**
     * 当前租户编号
     */
    private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();
    /**
     * 是否忽略用户
     */
    private static final ThreadLocal<Boolean> IGNORE = new ThreadLocal<>();

    /**
     * 获取租户编号
     *
     * @return 租户编号
     */
    public static String getTenantId() {
        return TENANT_ID.get();
    }

    /**
     * 获得租户编号。如果不存在，则抛出 NullPointerException 异常
     *
     * @return 租户编号
     */
    public static String getRequiredThreadId() {
        String tenantId = getTenantId();
        if (Objects.isNull(tenantId)) {
            throw new NullPointerException("TenantContextHolder 不存在租户编号！可参考文档：" + DocumentEnum.TENANT.getUrl());
        }
        return tenantId;
    }

    public static void setTenantId(String tenantId) {
        TENANT_ID.set(tenantId);
    }

    public static void setIgnore(Boolean ignore) {
        IGNORE.set(ignore);
    }

    /**
     * 当前是否忽略租户
     *
     * @return 是否忽略
     */
    public static boolean isIgnore() {
        return Boolean.TRUE.equals(IGNORE.get());
    }

    public static void clear() {
        TENANT_ID.remove();
        IGNORE.remove();
    }
}
