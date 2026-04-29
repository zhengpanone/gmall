package com.zp.gmall.module.member.service.impl;

import com.zp.gmall.module.member.discount.DiscountStrategyRouter;
import com.zp.gmall.module.member.service.IPriceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PriceServiceImpl implements IPriceService {

    private final DiscountStrategyRouter router;

    public PriceServiceImpl(DiscountStrategyRouter router) {
        this.router = router;
    }

    @Override
    public BigDecimal calculatePrice(String memberType, BigDecimal originalPrice) {
        return router.calculate(memberType, originalPrice);
    }
}
