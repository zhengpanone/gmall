package com.zp.coupon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author zhengpanone
 * @since 2022-07-30
 */
@RestController
public class TestController {
    @GetMapping("/")
    public String test(){
        return "gmall-coupon";
    }
}
