package com.zp.module.system.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author : zhengpanone
 * Date : 2024/7/31 18:02
 * Version : v1.0.0
 * Description: TODO
 */
@Getter
@AllArgsConstructor
public enum RoleTypeEnum {
    /**
     * 内置角色
     */
    SYSTEM(1),
    /**
     * 自定义角色
     */
    CUSTOM(2);

    private final Integer type;
}
