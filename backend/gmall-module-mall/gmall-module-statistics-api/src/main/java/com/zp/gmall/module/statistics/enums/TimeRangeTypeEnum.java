package com.zp.gmall.module.statistics.enums;

import com.zp.gmall.framework.common.core.Valuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 02:02
 * Version : v1.0.0
 * Description: 时间范围类型的枚举
 */
@AllArgsConstructor
@Getter
public enum TimeRangeTypeEnum implements Valuable<String> {

    /**
     * 天
     */
    DAY("1"),
    /**
     * 周
     */
    WEEK("7"),
    /**
     * 月
     */
    MONTH("30"),
    /**
     * 年
     */
    YEAR("365"),
    ;


    /**
     * 类型
     */
    private final String type;


    @Override
    public String getValue() {
        return type;
    }

}
