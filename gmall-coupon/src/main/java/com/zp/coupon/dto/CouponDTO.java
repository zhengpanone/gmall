package com.zp.coupon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 优惠券信息
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
@ApiModel(value = "优惠券信息")
public class CouponDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]")
	private Integer couponType;

	@ApiModelProperty(value = "优惠券图片")
	private String couponImg;

	@ApiModelProperty(value = "优惠卷名字")
	private String couponName;

	@ApiModelProperty(value = "数量")
	private Integer num;

	@ApiModelProperty(value = "金额")
	private BigDecimal amount;

	@ApiModelProperty(value = "每人限领张数")
	private Integer perLimit;

	@ApiModelProperty(value = "使用门槛")
	private BigDecimal minPoint;

	@ApiModelProperty(value = "开始时间")
	private Date startTime;

	@ApiModelProperty(value = "结束时间")
	private Date endTime;

	@ApiModelProperty(value = "使用类型[0->全场通用；1->指定分类；2->指定商品]")
	private Integer useType;

	@ApiModelProperty(value = "备注")
	private String note;

	@ApiModelProperty(value = "发行数量")
	private Integer publishCount;

	@ApiModelProperty(value = "已使用数量")
	private Integer useCount;

	@ApiModelProperty(value = "领取数量")
	private Integer receiveCount;

	@ApiModelProperty(value = "可以领取的开始日期")
	private Date enableStartTime;

	@ApiModelProperty(value = "可以领取的结束日期")
	private Date enableEndTime;

	@ApiModelProperty(value = "优惠码")
	private String code;

	@ApiModelProperty(value = "可以领取的会员等级[0->不限等级，其他-对应等级]")
	private Integer memberLevel;

	@ApiModelProperty(value = "发布状态[0-未发布，1-已发布]")
	private Integer publish;


}