package com.zp.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * spu属性值
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("pms_product_attr_value")
public class ProductAttrValueEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
	private Long spuId;
    /**
     * 属性id
     */
	private Long attrId;
    /**
     * 属性名
     */
	private String attrName;
    /**
     * 属性值
     */
	private String attrValue;
    /**
     * 顺序
     */
	private Integer attrSort;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】
     */
	private Integer quickShow;
}