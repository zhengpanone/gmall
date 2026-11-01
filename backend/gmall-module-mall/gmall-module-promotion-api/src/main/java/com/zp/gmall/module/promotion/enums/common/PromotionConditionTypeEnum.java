package com.zp.gmall.module.promotion.enums.common;

import com.zp.gmall.framework.common.core.Valuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 01:30
 * Version : v1.0.0
 * Description: 营销的条件类型枚举
 */
@AllArgsConstructor
@Getter
public enum PromotionConditionTypeEnum implements Valuable<String> {
    PRICE("10", "满 N 元"),
    COUNT("20", "满 N 件");

    /**
     * 类型值
     */
    private final String type;

    /**
     * 类型名
     */
    private final String name;


    @Override
    public String getValue() {
        return type;
    }
}
