package com.zp.coupon.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 优惠券领取历史记录
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
public class CouponHistoryExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "优惠券id")
    private Long couponId;
    @Excel(name = "会员id")
    private Long memberId;
    @Excel(name = "会员名字")
    private String memberNickName;
    @Excel(name = "获取方式[0->后台赠送；1->主动领取]")
    private Integer getType;
    @Excel(name = "创建时间")
    private Date createTime;
    @Excel(name = "使用状态[0->未使用；1->已使用；2->已过期]")
    private Integer useType;
    @Excel(name = "使用时间")
    private Date useTime;
    @Excel(name = "订单id")
    private Long orderId;
    @Excel(name = "订单号")
    private Long orderSn;

}