package com.zp.gmall.module.member.discount;

import java.math.BigDecimal;

/**
 * 折扣策略接口
 */
public interface DiscountStrategy {

    boolean supports(String memberType);

    BigDecimal apply(BigDecimal price);
}
