package com.zp.gmall.module.promotion.entity.coupon;

import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 00:50
 * Version : v1.0.0
 * Description: 优惠劵
 */
public class CouponDO extends BaseDO {
    private static final long serialVersionUID = 1L;

    /**
     * 优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]
     */
    private Integer couponType;
    /**
     * 优惠券图片
     */
    private String couponImg;
    /**
     * 优惠卷名字
     */
    private String couponName;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 每人限领张数
     */
    private Integer perLimit;
    /**
     * 使用门槛
     */
    private BigDecimal minPoint;
    /**
     * 生效开始时间
     */
    private LocalDateTime validStartTime;
    /**
     * 生效结束时间
     */
    private LocalDateTime validEndTime;
    /**
     * 使用类型[0->全场通用；1->指定分类；2->指定商品]
     */
    private Integer useType;
    /**
     * 备注
     */
    private String note;
    /**
     * 发行数量
     */
    private Integer publishCount;
    /**
     * 已使用数量
     */
    private Integer useCount;
    /**
     * 领取数量
     */
    private Integer receiveCount;
    /**
     * 可以领取的开始日期
     */
    private LocalDateTime enableStartTime;
    /**
     * 可以领取的结束日期
     */
    private LocalDateTime enableEndTime;
    /**
     * 优惠码
     */
    private String code;
    /**
     * 可以领取的会员等级[0->不限等级，其他-对应等级]
     */
    private Integer memberLevel;
    /**
     * 发布状态[0-未发布，1-已发布]
     */
    private Integer publish;
}
