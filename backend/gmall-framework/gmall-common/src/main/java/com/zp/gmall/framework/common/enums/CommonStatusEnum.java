package com.zp.gmall.framework.common.enums;

import com.zp.gmall.framework.common.core.Valuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 通用状态枚举
 *
 * @author 芋道源码
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum implements Valuable<String> {

    ENABLE("1", "开启"),
    DISABLE("0", "关闭");

    /**
     * 枚举值
     */
    private final String code;
    /**
     * 枚举描述
     */
    private final String name;

    @Override
    public String getValue() {
        return code;
    }

    public static boolean isEnable(String value) {
        return ENABLE.code.equals(value);
    }

    public static boolean isDisable(String value) {
        return DISABLE.code.equals(value);
    }

    public static String getLabelByValue(String value) {

        return Arrays.stream(values())
                .filter(item -> item.code.equals(value))
                .findFirst()
                .map(CommonStatusEnum::getName)
                .orElse("");
    }

}
