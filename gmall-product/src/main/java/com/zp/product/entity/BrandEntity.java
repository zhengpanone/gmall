package com.zp.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 品牌
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("pms_brand")
public class BrandEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
	private Long brandId;
    /**
     * 品牌名
     */
	private String name;
    /**
     * 品牌logo地址
     */
	private String logo;
    /**
     * 介绍
     */
	private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
	private Integer showStatus;
    /**
     * 检索首字母
     */
	private String firstLetter;
    /**
     * 排序
     */
	private Integer sort;
}