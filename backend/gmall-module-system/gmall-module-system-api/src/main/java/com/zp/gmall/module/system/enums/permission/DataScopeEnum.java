package com.zp.gmall.module.system.enums.permission;

import com.zp.gmall.framework.common.core.Valuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author : zhengpanone
 * Date : 2024/7/31 18:04
 * Version : v1.0.0
 * Description: 数据范围枚举类
 * 用于实现数据级别的权限
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum implements Valuable<String> {
    // 全部数据权限
    ALL("1"),
    // 指定部门数据权限
    DEPT_CUSTOM("2"),
    // 部门数据权限
    DEPT_ONLY("3"),
    // 部门及以下数据权限
    DEPT_AND_CHILD("4"),
    // 仅本人数据权限
    SELF("5");
    /**
     * 范围
     */
    private final String scope;

    @Override
    public String getValue() {
        return scope;
    }
}
