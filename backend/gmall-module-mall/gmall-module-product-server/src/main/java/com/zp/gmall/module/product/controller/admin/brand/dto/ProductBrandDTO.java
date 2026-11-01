package com.zp.gmall.module.product.controller.admin.brand.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.zp.gmall.framework.common.domain.ViewGroup;
import com.zp.gmall.framework.validation.group.CreateGroup;
import com.zp.gmall.framework.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:52
 * Version : v1.0.0
 * Description:
 */
@Schema(name = "商品品牌DTO", description = "管理后台 - 商品品牌DTO")
@Data
public class ProductBrandDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "品牌ID", example = "1")
    @NotNull(message = "品牌ID不能为空", groups = UpdateGroup.class)
    @JsonView(ViewGroup.UpdateView.class)
    private String id;

    @Schema(description = "品牌名称", example = "小米")
    @NotBlank(message = "品牌名称不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Size(max = 50, message = "品牌名称长度不能超过50个字符", groups = {CreateGroup.class, UpdateGroup.class})
    private String name;

    @Schema(description = "品牌logo", example = "https://gmall.com/brand/logo.png")
    private String logoUrl;

    @Schema(description = "品牌大图", example = "https://gmall.com/brand/big.png")
    private String bigPicUrl;

    @Schema(description = "品牌描述", example = "小米手机")
    private String description;

    @Schema(description = "品牌排序", example = "1")
    private String sort;

    @Schema(description = "品牌状态", example = "1")
    private Integer status;


}
