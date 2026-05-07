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
    private final String value;
    /**
     * 枚举描述
     */
    private final String label;

    @Override
    public String getValue() {
        return value;
    }

    public static boolean isEnable(String value) {
        return ENABLE.value.equals(value);
    }

    public static boolean isDisable(String value) {
        return DISABLE.value.equals(value);
    }

    public static String getLabelByValue(String value) {

        return Arrays.stream(values())
                .filter(item -> item.value.equals(value))
                .findFirst()
                .map(CommonStatusEnum::getLabel)
                .orElse("");
    }

}
