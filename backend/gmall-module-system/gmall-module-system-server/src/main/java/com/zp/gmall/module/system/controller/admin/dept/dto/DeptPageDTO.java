package com.zp.gmall.module.system.controller.admin.dept.dto;


import com.zp.gmall.framework.common.domain.dto.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.controller.admin.dept.dto
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Schema(name = "部门分页查询DTO", description = "管理后台 - 部门分页查询DTO")
@Data
public class DeptPageDTO extends PageParam {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "部门编码")
    private String deptCode;
}
