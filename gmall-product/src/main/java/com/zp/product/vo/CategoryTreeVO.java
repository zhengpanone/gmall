package com.zp.product.vo;


import com.zp.common.entity.TreeJson;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 商品三级分类
 *
 * @author zhengpanone zhengpanone@hotmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "商品分类")
public class CategoryTreeVO extends TreeJson {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类id")
    private Long catId;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "父分类id")
    private Long parentCid;

    @ApiModelProperty(value = "层级")
    private Integer catLevel;

    @ApiModelProperty(value = "是否显示[0-不显示，1显示]")
    private Integer showStatus;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "图标地址")
    private String icon;

    @ApiModelProperty(value = "计量单位")
    private String productUnit;

    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

}
