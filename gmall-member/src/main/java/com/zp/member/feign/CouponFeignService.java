package com.zp.member.feign;

import com.zp.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * CouponFeignService
 *
 * @author zhengpanone
 * @since 2022-07-31
 */
@FeignClient("gmall-coupon")
public interface CouponFeignService {
    @GetMapping("/coupon/coupon/list")
    public Result<List<Object>> memberCoupons();
}
