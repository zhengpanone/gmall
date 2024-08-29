package com.zp.module.system.enums.permission;

import com.zp.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Author : zhengpanone
 * Date : 2024/7/31 18:04
 * Version : v1.0.0
 * Description: 数据范围枚举类
 * 用于实现数据级别的权限
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum implements IntArrayValuable {
    // 全部数据权限
    ALL(1),
    // 指定部门数据权限
    DEPT_CUSTOM(2),
    // 部门数据权限
    DEPT_ONLY(3),
    // 部门及以下数据权限
    DEPT_AND_CHILD(4),
    // 仅本人数据权限
    SELF(5);
    /**
     * 范围
     */
    private final Integer scope;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(DataScopeEnum::getScope).toArray();

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
