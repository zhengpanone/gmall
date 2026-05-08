package com.zp.gmall.module.system.controller.admin.dict.dto;

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

import java.io.Serial;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "管理后台 - 字典创建DTO")
@Data
public class DictTypeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonView(ViewGroup.UpdateView.class)
    @Schema(description = "字典ID", example = "1")
    @NotNull(message = "字典ID不能为空", groups = UpdateGroup.class)
    private String id;

    @NotBlank(message = "字典类型编码不能为空")
    @Size(max = 50, message = "字典编码长度不能超过50")
    @Schema(description = "字典编码", example = "status")
    private String typeCode;


    @NotBlank(message = "字典名称不能为空")
    @Size(max = 100, message = "字典名称长度不能超过100")
    @Schema(description = "字典名称", example = "状态")
    private String typeName;

    @NotNull(message = "类型不能为空")
    @Schema(description = "类型：1-系统字典 2-业务字典", example = "1")
    private Integer type;

    @Schema(description = "备注", example = "数据状态")
    @Size(max = 500, message = "备注长度不能超过500")
    private String remark;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序不能为空", example = "1")
    private Integer sort;

    /**
     * {@link CommonStatusEnum}
     */
    @NotNull(message = "状态不能为空")
    @Schema(description = "状态：0-禁用 1-启用", example = "1")
    @Builder.Default
    private Integer status = 1;
}
