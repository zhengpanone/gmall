package com.zp.module.system.controller.admin.tenant.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 12:00
 * Version : v1.0.0
 * Description: 租户精简 Response VO
 */
@Schema(description = "管理后台 - 租户精简 Response VO")
@Data
public class TenantSimpleVO {
    @Schema(description = "租户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "租户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道")
    private String name;
}
