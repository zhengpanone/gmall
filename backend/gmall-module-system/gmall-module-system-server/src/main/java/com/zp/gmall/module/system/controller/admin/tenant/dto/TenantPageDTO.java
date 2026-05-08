package com.zp.gmall.module.system.controller.admin.tenant.dto;

import com.zp.gmall.framework.common.domain.dto.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 *
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
@Schema(title = "租户分页查询DTO", description = "管理后台 - 租户分页查询DTO")
public class TenantPageDTO extends PageParam {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "租户名称", description = "租户名称", example = "租户名称")
    private String name;

    @Schema(title = "租户编码", description = "租户编码", example = "租户编码")
    private String code;

    @Schema(title = "租户描述", description = "租户描述", example = "租户描述")
    private String description;
}
