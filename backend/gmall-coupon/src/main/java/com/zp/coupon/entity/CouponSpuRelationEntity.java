package com.zp.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 优惠券与产品关联
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sms_coupon_spu_relation")
public class CouponSpuRelationEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 优惠券id
     */
	private Long couponId;
    /**
     * spu_id
     */
	private Long spuId;
    /**
     * spu_name
     */
	private String spuName;
}