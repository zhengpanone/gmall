package com.zp.module.member.discount;

import com.zp.module.member.discount.enums.MemberType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 策略路由中心
 */
@Component
public class DiscountStrategyRouter {
    private final List<DiscountStrategy> strategies;

    public DiscountStrategyRouter(List<DiscountStrategy> strategies) {
        this.strategies = strategies;
    }

    public BigDecimal calculate(String memberType, BigDecimal price) {
        // 优先使用可以配置策略
        for (DiscountStrategy strategy : strategies) {
            if (strategy.supports(memberType)) {
                return strategy.apply(price);
            }
        }
        // 枚举兜底
        return MemberType.valueOf(memberType).apply(price);
    }
}
