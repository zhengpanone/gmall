package com.zp.gmall.module.product.controller.admin.brand.dto;

import com.zp.gmall.framework.common.domain.dto.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:52
 * Version : v1.0.0
 * Description:
 */
@Data
@Schema(description = "商品品牌分页DTO")
public class ProductBrandPageDTO extends PageParam {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "品牌名称", example = "小米")
    private String name;
}
