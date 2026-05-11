package com.zp.gmall.module.system.controller.admin.log.dto;

import com.zp.gmall.framework.common.domain.dto.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Schema(description = "管理后台 - 操作日志分页DTO")
@Data
public class OperLogPageDTO extends PageParam {
    @Schema(description = "模块标题", example = "用户管理")
    private String title;

    @Schema(description = "操作人", example = "zhengpan")
    private String operName;

    @Schema(description = "请求地址", example = "http://localhost:8080/api/admin/user/list")
    private String operUrl;

    @Schema(description = "请求方法", example = "GET")
    private String method;

    @Schema(description = "操作状态：0-失败 1-成功", example = "1")
    private String status;

    @Schema(description = "操作时间", example = "2026-05-08 12:00:00")
    private String operTime;
}
