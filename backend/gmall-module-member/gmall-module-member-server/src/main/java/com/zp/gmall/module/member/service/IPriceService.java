package com.zp.gmall.module.member.service;

import java.math.BigDecimal;


public interface IPriceService {
    BigDecimal calculatePrice(String memberType, BigDecimal originalPrice);
}
