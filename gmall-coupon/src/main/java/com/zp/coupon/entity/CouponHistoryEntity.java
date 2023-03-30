package com.zp.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 优惠券领取历史记录
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sms_coupon_history")
public class CouponHistoryEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 优惠券id
     */
	private Long couponId;
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
	private Date createTime;
    /**
     * 使用状态[0->未使用；1->已使用；2->已过期]
     */
	private Integer useType;
    /**
     * 使用时间
     */
	private Date useTime;
    /**
     * 订单id
     */
	private Long orderId;
    /**
     * 订单号
     */
	private Long orderSn;
}