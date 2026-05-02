package com.zp.gmall.module.system.controller.admin.dict.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(description = "管理后台 - 字典数据 Response VO")
@Data
@Builder
public class DictDataVO {

    @Schema(description = "字典数据ID", example = "1")
    private String id;

    @Schema(description = "字典类型ID", example = "1")
    private String typeId;

    @Schema(description = "字典类型Code", example = "1")
    private String typeCode;

    @Schema(description = "字典编码", example = "1")
    private String dataCode;

    @Schema(description = "字典名称", example = "1")
    private String dataName;

    @Schema(description = "备注", example = "1")
    private String remark;

    @Schema(description = "排序", example = "1")
    private Integer sort;

    @Schema(description = "状态", example = "1")
    private Integer status;
}
