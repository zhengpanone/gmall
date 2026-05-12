package com.zp.gmall.module.product.controller.admin.category.dto;

import com.zp.gmall.framework.common.domain.dto.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Data
@Schema(name = "商品分类分页查询DTO", description = "商品分类分页查询参数")
public class ProductCategoryPageDTO extends PageParam {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "分类名称", example = "手机")
    private String name;
}
