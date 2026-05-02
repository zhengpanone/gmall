package com.zp.gmall.module.system.controller.admin.dict.dto;

import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Schema(description = "管理后台 - 字典创建DTO")
@Data
public class DictTypeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "字典ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String id;

    @NotBlank(message = "字典类型编码不能为空")
    @Size(max = 50, message = "字典编码长度不能超过50")
    @Schema(description = "字典编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "status")
    private String typeCode;


    @NotBlank(message = "字典名称不能为空")
    @Size(max = 100, message = "字典名称长度不能超过100")
    @Schema(description = "字典名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "状态")
    private String typeName;

    @NotNull(message = "类型不能为空")
    @Schema(description = "类型：1-系统字典 2-业务字典", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer type;

    @Schema(description = "备注", requiredMode = Schema.RequiredMode.REQUIRED, example = "数据状态")
    @Size(max = 500, message = "备注长度不能超过500")
    private String remark;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序不能为空", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer sort;

    /**
     * {@link CommonStatusEnum}
     */
    @NotNull(message = "状态不能为空")
    @Schema(description = "状态：0-禁用 1-启用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer status;
}
