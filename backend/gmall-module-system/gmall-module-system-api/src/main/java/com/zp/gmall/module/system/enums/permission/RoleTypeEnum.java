package com.zp.gmall.module.system.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Author : zhengpanone
 * Date : 2024/7/31 18:02
 * Version : v1.0.0
 * Description:
 */
@Getter
@AllArgsConstructor
public enum RoleTypeEnum {
    /**
     * 内置角色
     */
    SYSTEM(1, "系统内置"),
    /**
     * 自定义角色
     */
    CUSTOM(2, "自定义");

    private final Integer type;
    private final String label;

    /**
     * 根据type获取枚举
     *
     * @param type 类型
     * @return 枚举
     */
    public static RoleTypeEnum getByType(Integer type) {
        return Arrays.stream(values())
                .filter(e -> e.getType().equals(type))
                .findFirst()
                .orElse(null);
    }
}
