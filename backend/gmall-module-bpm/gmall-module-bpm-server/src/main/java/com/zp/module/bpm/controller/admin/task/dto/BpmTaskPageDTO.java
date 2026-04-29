package com.zp.module.bpm.controller.admin.task.dto;

import com.zp.gmall.framework.common.domain.dto.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BpmTaskPageDTO extends PageParam {

    @Schema(description = "任务名称")
    private String name;

    @Schema(description = "流程分类")
    private String category;

    @Schema(description = "流程定义Key")
    private String processDefinitionKey;
}
