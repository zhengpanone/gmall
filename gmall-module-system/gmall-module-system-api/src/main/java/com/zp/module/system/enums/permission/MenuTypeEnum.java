package com.zp.module.system.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author : zhengpanone
 * Date : 2024/7/31 18:18
 * Version : v1.0.0
 * Description: 菜单类型枚举类
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {
    // 目录
    DIR(1),
    // 菜单
    MENU(2),
    // 按钮
    BUTTON(3);
    /**
     * 类型
     */
    private final Integer type;
}
