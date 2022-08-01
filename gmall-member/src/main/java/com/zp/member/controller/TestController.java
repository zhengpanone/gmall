package com.zp.member.controller;

import com.zp.common.utils.Result;
import com.zp.member.feign.CouponFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TestController
 *
 * @author zhengpanone
 * @since 2022-07-30
 */
@RestController
public class TestController {
    @Autowired
    CouponFeignService couponFeignService;

    @GetMapping("/")
    public String test() {
        return "gmall-member";
    }

    @GetMapping("/coupons")
    public Result<List<Object>> getCoupon() {
        return couponFeignService.memberCoupons();
    }
}
