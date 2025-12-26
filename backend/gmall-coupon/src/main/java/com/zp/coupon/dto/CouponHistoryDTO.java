package com.zp.coupon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 优惠券领取历史记录
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
@ApiModel(value = "优惠券领取历史记录")
public class CouponHistoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "优惠券id")
	private Long couponId;

	@ApiModelProperty(value = "会员id")
	private Long memberId;

	@ApiModelProperty(value = "会员名字")
	private String memberNickName;

	@ApiModelProperty(value = "获取方式[0->后台赠送；1->主动领取]")
	private Integer getType;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "使用状态[0->未使用；1->已使用；2->已过期]")
	private Integer useType;

	@ApiModelProperty(value = "使用时间")
	private Date useTime;

	@ApiModelProperty(value = "订单id")
	private Long orderId;

	@ApiModelProperty(value = "订单号")
	private Long orderSn;


}