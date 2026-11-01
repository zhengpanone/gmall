package com.zp.gmall.framework.common.enums;

import com.zp.gmall.framework.common.core.Valuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 时间间隔的枚举
 *
 * @author dhb52
 */
@Getter
@AllArgsConstructor
public enum DateIntervalEnum implements Valuable<String> {

    DAY("1", "天"),
    WEEK("2", "周"),
    MONTH("3", "月"),
    QUARTER("4", "季度"),
    YEAR("5", "年");

    public static final String[] ARRAYS = Arrays.stream(values()).map(DateIntervalEnum::getInterval).toArray(String[]::new);

    /**
     * 类型
     */
    private final String interval;
    /**
     * 名称
     */
    private final String name;

    @Override
    public String getValue() {
        return interval;
    }

}