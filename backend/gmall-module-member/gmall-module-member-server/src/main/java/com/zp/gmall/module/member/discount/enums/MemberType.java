package com.zp.gmall.module.member.discount.enums;

import java.math.BigDecimal;

/**
 * 枚举会员类型
 */
public enum MemberType {
    REGULAR(new BigDecimal("0.98")),
    VIP(new BigDecimal("0.95")),
    PREMIUM(new BigDecimal("0.90"));

    private final BigDecimal defaultDiscount;

    MemberType(BigDecimal defaultDiscount) {
        this.defaultDiscount = defaultDiscount;
    }

    public BigDecimal apply(BigDecimal price) {
        return price.multiply(defaultDiscount);
    }

}
