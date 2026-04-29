package com.zp.gmall.module.promotion.convert.coupon;

import com.zp.gmall.module.promotion.controller.admin.banner.dto.BannerDTO;
import com.zp.gmall.module.promotion.controller.admin.banner.vo.BannerVO;
import com.zp.gmall.module.promotion.controller.admin.coupon.dto.CouponDTO;
import com.zp.gmall.module.promotion.controller.admin.coupon.vo.CouponVO;
import com.zp.gmall.module.promotion.entity.banner.BannerDO;
import com.zp.gmall.module.promotion.entity.coupon.CouponDO;
import org.mapstruct.Mapper;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 01:52
 * Version : v1.0.0
 * Description:
 */
@Mapper
public interface CouponConvert {
    CouponDO convert(CouponDTO dto);

    CouponVO convert(CouponDO couponDO);
}
