package com.zp.gmall.module.product.controller.admin.brand.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "商品品牌VO")
public class ProductBrandVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "品牌ID", example = "1")
    private String id;

    @Schema(description = "品牌名称", example = "小米")
    private String name;
}
