package com.zp.module.member.controller;

import com.zp.module.member.service.IPriceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/price")
public class PriceController {
    private final IPriceService priceService;

    public PriceController(IPriceService priceService) {
        this.priceService = priceService;
    }

    @RequestMapping("/calculate")
    public BigDecimal calculatePrice(@RequestParam("memberType") String memberType, @RequestParam("originalPrice") BigDecimal originalPrice) {
        return priceService.calculatePrice(memberType, originalPrice);
    }
}
