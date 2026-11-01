package com.zp.gmall.module.product.controller.admin.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Collection;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-12
 */
@Schema(description = "管理后台 - 商品分类列表查询 Request DTO")
@Data
public class ProductCategoryListDTO {

    @Schema(description = "分类名称", example = "办公文具")
    private String name;

    @Schema(description = "开启状态", example = "0")
    private Integer status;

    @Schema(description = "父分类编号", example = "1")
    private Long parentId;

    @Schema(description = "父分类编号数组", example = "1,2,3")
    private Collection<String> parentIds;

}
