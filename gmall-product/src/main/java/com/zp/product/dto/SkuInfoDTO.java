package com.zp.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * sku信息
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Data
@ApiModel(value = "sku信息")
public class SkuInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "skuId")
	private Long skuId;

	@ApiModelProperty(value = "spuId")
	private Long spuId;

	@ApiModelProperty(value = "sku名称")
	private String skuName;

	@ApiModelProperty(value = "sku介绍描述")
	private String skuDesc;

	@ApiModelProperty(value = "所属分类id")
	private Long catalogId;

	@ApiModelProperty(value = "品牌id")
	private Long brandId;

	@ApiModelProperty(value = "默认图片")
	private String skuDefaultImg;

	@ApiModelProperty(value = "标题")
	private String skuTitle;

	@ApiModelProperty(value = "副标题")
	private String skuSubtitle;

	@ApiModelProperty(value = "价格")
	private BigDecimal price;

	@ApiModelProperty(value = "销量")
	private Long saleCount;


}