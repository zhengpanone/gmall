package com.zp.module.system.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author : zhengpanone
 * Date : 2023/12/20 17:27
 * Version : v1.0.0
 * Description: 性别的枚举值
 */
@Getter
@AllArgsConstructor
public enum SexEnum {
    /**
     * 男
     */
    MALE(1),
    /**
     * 女
     */
    FEMALE(2),
    /**
     * 未知
     */
    UNKNOWN(3);
    /**
     * 性别
     */
    private final Integer sex;
}
