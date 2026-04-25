package com.zp.gmall.module.system.controller.admin.dict.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "管理后台 - 字典更新DTO")
@Data
public class DictUpdateDTO {

    @NotBlank(message = "字典ID不能为空")
    @Schema(description = "字典ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "f47ac10b-58cc-4372-a567-0e02b2c3d47")
    private String id;

    @NotBlank(message = "字典编码不能为空")
    @Size(max = 50, message = "字典编码长度不能超过50")
    @Schema(description = "字典编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao")
    private String dictCode;

    @NotBlank(message = "字典名称不能为空")
    @Size(max = 100, message = "字典名称长度不能超过100")
    @Schema(description = "字典名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao")
    private String dictName;

    @NotNull(message = "字典类型不能为空")
    @Schema(description = "字典类型：1-系统字典 2-业务字典", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao")
    private Integer dictType;

    @Schema(description = "备注", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao")
    @Size(max = 500, message = "备注长度不能超过500")
    private String remark;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序不能为空", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao")
    private Integer sort;

    @NotNull(message = "状态不能为空")
    @Schema(description = "状态：0-禁用 1-启用", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao")
    private Integer status;
}
