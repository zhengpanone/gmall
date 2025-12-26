package com.zp.coupon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 优惠券与产品关联
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
@ApiModel(value = "优惠券与产品关联")
public class CouponSpuRelationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "优惠券id")
	private Long couponId;

	@ApiModelProperty(value = "spu_id")
	private Long spuId;

	@ApiModelProperty(value = "spu_name")
	private String spuName;


}