package com.zp.product.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 商品属性
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Data
public class AttrExcel {
    @Excel(name = "属性id")
    private Long attrId;
    @Excel(name = "属性名")
    private String attrName;
    @Excel(name = "是否需要检索[0-不需要，1-需要]")
    private Integer searchType;
    @Excel(name = "属性图标")
    private String icon;
    @Excel(name = "可选值列表[用逗号分隔]")
    private String valueSelect;
    @Excel(name = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
    private Integer attrType;
    @Excel(name = "启用状态[0 - 禁用，1 - 启用]")
    private Long enable;
    @Excel(name = "所属分类")
    private Long catelogId;
    @Excel(name = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
    private Integer showDesc;

}