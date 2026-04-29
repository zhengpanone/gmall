package com.zp.module.bpm.controller.admin.task.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 流程任务VO")
public class BpmTaskVO {
    @Schema(description = "任务ID")
    private String id;
}
