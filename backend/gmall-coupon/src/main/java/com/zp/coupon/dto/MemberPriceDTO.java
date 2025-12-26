package com.zp.coupon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 商品会员价格
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
@ApiModel(value = "商品会员价格")
public class MemberPriceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "sku_id")
	private Long skuId;

	@ApiModelProperty(value = "会员等级id")
	private Long memberLevelId;

	@ApiModelProperty(value = "会员等级名")
	private String memberLevelName;

	@ApiModelProperty(value = "会员对应价格")
	private BigDecimal memberPrice;

	@ApiModelProperty(value = "可否叠加其他优惠[0-不可叠加优惠，1-可叠加]")
	private Integer addOther;


}