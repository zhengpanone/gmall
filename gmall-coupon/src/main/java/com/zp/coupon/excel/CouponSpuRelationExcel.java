package com.zp.coupon.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 优惠券与产品关联
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
public class CouponSpuRelationExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "优惠券id")
    private Long couponId;
    @Excel(name = "spu_id")
    private Long spuId;
    @Excel(name = "spu_name")
    private String spuName;

}