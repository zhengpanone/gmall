package com.zp.gmall.module.system.controller.admin.dict.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 字典VO")
@Data
public class DictTypeVO {
    @Schema(description = "字典ID", example = "1")
    private String id;

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

    @Schema(description = "创建时间", example = "2025-06-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", example = "2025-06-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
