package com.zp.gmall.module.promotion.entity.coupon;

import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;

import java.time.LocalDateTime;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 00:54
 * Version : v1.0.0
 * Description:
 */
public class CouponHistoryDO extends BaseDO {
    private static final long serialVersionUID = 1L;

    /**
     * 优惠券id
     */
    private String couponId;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 会员名字
     */
    private String memberNickName;
    /**
     * 获取方式[0->后台赠送；1->主动领取]
     */
    private Integer getType;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 使用状态[0->未使用；1->已使用；2->已过期]
     */
    private Integer useType;
    /**
     * 使用时间
     */
    private LocalDateTime useTime;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单号
     */
    private Long orderSn;
}
