package com.zp.module.system.controller.admin.dept.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 12:42
 * Version : v1.0.0
 * Description:
 */
@Schema(description = "管理后台 - 部门列表 DTO")
@Data
@Accessors(chain = true)
public class DeptListDTO {
    @Schema(description = "部门名称，模糊匹配", example = "芋道")
    private String name;

    @Schema(description = "展示状态，参见 CommonStatusEnum 枚举类", example = "1")
    private Integer status;
}
