package com.zp.module.bpm.controller.admin.definition.dto;

import com.zp.gmall.framework.common.domain.dto.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Schema(description = "管理后台 - 流程分类分页 Request DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BpmCategoryPageDTO extends PageParam {

    @Schema(description = "分类编码")
    private String code;

    @Schema(description = "分类名称")
    private String name;

}
