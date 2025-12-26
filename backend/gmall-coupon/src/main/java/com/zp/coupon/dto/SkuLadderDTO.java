package com.zp.coupon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 商品阶梯价格
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
@ApiModel(value = "商品阶梯价格")
public class SkuLadderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "spu_id")
	private Long skuId;

	@ApiModelProperty(value = "满几件")
	private Integer fullCount;

	@ApiModelProperty(value = "打几折")
	private BigDecimal discount;

	@ApiModelProperty(value = "折后价")
	private BigDecimal price;

	@ApiModelProperty(value = "是否叠加其他优惠[0-不可叠加，1-可叠加]")
	private Integer addOther;


}