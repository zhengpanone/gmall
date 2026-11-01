package com.zp.gmall.module.system.controller.admin.config.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.zp.gmall.framework.common.domain.ViewGroup;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.validation.group.CreateGroup;
import com.zp.gmall.framework.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.controller.admin.config.dto
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-06
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "管理后台 - 参数创建DTO")
@Data
public class ConfigDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonView(ViewGroup.UpdateView.class)
    @Schema(description = "参数ID", example = "1")
    @NotNull(message = "参数ID不能为空", groups = UpdateGroup.class)
    private String id;

    @NotBlank(message = "参数分类不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Size(max = 50, message = "参数分类长度不能超过50个字符", groups = {CreateGroup.class, UpdateGroup.class})
    @Size(max = 50, message = "参数分类长度不能超过50个字符")
    @Schema(description = "参数分类", example = "系统参数")
    private String category;

    @NotBlank(message = "参数名称不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Size(max = 50, message = "参数名称长度不能超过50个字符", groups = {CreateGroup.class, UpdateGroup.class})
    @Size(max = 50, message = "参数名称长度不能超过50个字符")
    @Schema(description = "参数名称", example = "系统名称")
    private String configName;

    @NotBlank(message = "参数键不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Size(max = 50, message = "参数键长度不能超过50个字符", groups = {CreateGroup.class, UpdateGroup.class})
    @Size(max = 50, message = "参数键长度不能超过50个字符")
    @Schema(description = "参数键", example = "system_name")
    private String configKey;

    @NotBlank(message = "参数值不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Size(max = 500, message = "参数值长度不能超过500个字符", groups = {CreateGroup.class, UpdateGroup.class})
    @Size(max = 500, message = "参数值长度不能超过500个字符")
    @Schema(description = "参数值", example = "GMall")
    private String configValue;

    @NotBlank(message = "参数类型不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Size(max = 50, message = "参数类型长度不能超过50个字符", groups = {CreateGroup.class, UpdateGroup.class})
    @Size(max = 50, message = "参数类型长度不能超过50个字符")
    @Schema(description = "参数类型", example = "system")
    private String configType;

    @Size(max = 500, message = "备注长度不能超过500个字符")
    @Schema(description = "备注", example = "系统名称")
    private String remark;

    /**
     * {@link CommonStatusEnum}
     */
    @NotNull(message = "状态不能为空")
    @Schema(description = "状态：0-禁用 1-启用", example = "1")
    @Range(min = 0, max = 1, message = "状态只能为0-1")
    @Builder.Default
    private Integer status = 1;
}
