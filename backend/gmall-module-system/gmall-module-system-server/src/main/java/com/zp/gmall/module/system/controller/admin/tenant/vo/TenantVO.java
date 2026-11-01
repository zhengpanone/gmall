package com.zp.gmall.module.system.controller.admin.tenant.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Data
@Schema(title = "租户VO", description = "租户值对象")
public class TenantVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "租户ID", description = "租户ID", example = "租户ID")
    private String id;

    @Schema(title = "租户名称", description = "租户名称", example = "租户名称")
    private String tenantName;

    @Schema(title = "租户编码", description = "租户编码", example = "租户编码")
    private String tenantCode;

    @Schema(title = "域名列表", description = "域名列表", example = "域名列表")
    private List<String> websites;

    @Schema(title = "租户描述", description = "租户描述", example = "租户描述")
    private String description;

    @Schema(title = "状态", description = "状态", example = "0")
    private String status;
}
