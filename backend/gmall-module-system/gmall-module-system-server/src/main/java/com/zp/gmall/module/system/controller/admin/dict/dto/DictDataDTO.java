package com.zp.gmall.module.system.controller.admin.dict.dto;

import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.validation.annotation.enumvalidation.InEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Schema(description = "管理后台 - 字典创建DTO")
@Data
public class DictDataDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "字典类型ID不能为空")
    @Schema(description = "字典类型：1-系统字典 2-业务字典", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao")
    private String typeId;

    @NotNull(message = "字典类型Code不能为空")
    private String typeCode;

    @NotBlank(message = "字典编码不能为空")
    @Size(max = 50, message = "字典编码长度不能超过50")
    @Schema(description = "字典编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao")
    private String dataCode;

    @NotBlank(message = "字典名称不能为空")
    @Size(max = 100, message = "字典名称长度不能超过100")
    @Schema(description = "字典名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao")
    private String dataName;

    @Schema(description = "备注", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao")
    @Size(max = 500, message = "备注长度不能超过500")
    private String remark;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序不能为空", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao")
    private Integer sort;


    @InEnum(enumClass = CommonStatusEnum.class, allowNull = false, allowEmpty = false)
    @NotNull(message = "状态不能为空")
    @Schema(description = "状态：0-禁用 1-启用", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao")
    private Integer status;
}
