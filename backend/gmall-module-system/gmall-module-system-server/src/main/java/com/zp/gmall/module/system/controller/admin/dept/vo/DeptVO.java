package com.zp.gmall.module.system.controller.admin.dept.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.controller.admin.dept.vo
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "管理后台 - 部门VO")
public class DeptVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "部门ID", example = "1")
    private String id;

    @Schema(description = "部门名称", example = "GMall")
    private String deptName;

    @Schema(description = "部门编码", example = "GMall")
    private String deptCode;

    @Schema(description = "父部门ID", example = "0")
    private String parentId;

    @Schema(description = "排序", example = "1")
    private Integer sort;

    @Schema(description = "状态：0-禁用 1-启用", example = "1")
    private Integer status;

    @Schema(description = "备注", example = "GMall")
    private String remark;

    @Schema(description = "创建时间", example = "2026-05-08 12:00:00")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", example = "2026-05-08 12:00:00")
    private LocalDateTime updateTime;
}
