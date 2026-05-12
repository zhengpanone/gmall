package com.zp.gmall.module.product.controller.admin.category.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.zp.gmall.framework.common.domain.ViewGroup;
import com.zp.gmall.framework.validation.group.CreateGroup;
import com.zp.gmall.framework.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Schema(name = "商品分类DTO", description = "商品分类DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "父分类ID", example = "0")
    @NotBlank(message = "父分类ID不能为空", groups = CreateGroup.class)
    private String parentId;

    @Schema(description = "分类ID", example = "1")
    @NotBlank(message = "分类ID不能为空", groups = UpdateGroup.class)
    @JsonView(ViewGroup.UpdateView.class)
    private String id;

    @Schema(description = "分类名称", example = "手机")
    @NotBlank(message = "分类名称不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Size(max = 50, message = "分类名称长度不能超过50个字符", groups = {CreateGroup.class, UpdateGroup.class})
    private String name;

    @Schema(description = "分类图标", example = "https://gmall.com/category/phone.png")
    private String picUrl;

    @Schema(description = "分类排序", example = "1")
    private Integer sort;

    @Schema(description = "分类状态", example = "1")
    private Integer status;
}
