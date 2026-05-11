package com.zp.gmall.module.system.controller.admin.tenant.vo;

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
@Data
@Schema(title = "租户套餐VO", description = "租户套餐VO")
public class TenantPackageVO {

    @Schema(title = "租户套餐ID", description = "租户套餐ID", example = "租户套餐ID")
    private String id;

    @Schema(title = "租户套餐名称", description = "租户套餐名称", example = "租户套餐名称")
    private String packageName;

    @Schema(title = "租户套餐编码", description = "租户套餐编码", example = "租户套餐编码")
    private String packageCode;

    @Schema(title = "租户套餐状态", description = "租户套餐状态", example = "租户套餐状态")
    private String status;

    @Schema(title = "租户套餐描述", description = "租户套餐描述", example = "租户套餐描述")
    private String description;
}
