package com.zp.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 商品属性
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Data
@ApiModel(value = "商品属性")
public class AttrDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "属性id")
	private Long attrId;

	@ApiModelProperty(value = "属性名")
	private String attrName;

	@ApiModelProperty(value = "是否需要检索[0-不需要，1-需要]")
	private Integer searchType;

	@ApiModelProperty(value = "属性图标")
	private String icon;

	@ApiModelProperty(value = "可选值列表[用逗号分隔]")
	private String valueSelect;

	@ApiModelProperty(value = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
	private Integer attrType;

	@ApiModelProperty(value = "启用状态[0 - 禁用，1 - 启用]")
	private Long enable;

	@ApiModelProperty(value = "所属分类")
	private Long catelogId;

	@ApiModelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
	private Integer showDesc;


}