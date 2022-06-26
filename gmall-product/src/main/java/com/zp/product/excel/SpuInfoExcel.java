package com.zp.product.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * spu信息
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Data
public class SpuInfoExcel {
    @Excel(name = "商品id")
    private Long id;
    @Excel(name = "商品名称")
    private String spuName;
    @Excel(name = "商品描述")
    private String spuDescription;
    @Excel(name = "所属分类id")
    private Long catalogId;
    @Excel(name = "品牌id")
    private Long brandId;
    @Excel(name = "")
    private BigDecimal weight;
    @Excel(name = "上架状态[0 - 下架，1 - 上架]")
    private Integer publishStatus;
    @Excel(name = "")
    private Date createTime;
    @Excel(name = "")
    private Date updateTime;

}