package com.zp.gmall.module.system.controller.admin.dict.dto;

import com.zp.gmall.framework.common.domain.dto.PageParam;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;

@Schema(description = "管理后台 - 字典创建DTO")
@Data
public class DictTypePageDTO extends PageParam {

    @Serial
    private static final long serialVersionUID = 1L;


    @Schema(description = "字典编码", example = "status")
    private String typeCode;


    @Schema(description = "字典名称", example = "状态")
    private String typeName;

    @Schema(description = "类型：1-系统字典 2-业务字典", example = "1")
    private Integer type;

    @Schema(description = "备注", example = "数据状态")
    private String remark;


    @Schema(description = "排序不能为空", example = "1")
    private Integer sort;

    /**
     * {@link CommonStatusEnum}
     */

    @Schema(description = "状态：0-禁用 1-启用", example = "1")
    private Integer status;
}
