package com.zp.gmall.module.system.controller.admin.config.dto;

import com.zp.gmall.framework.common.domain.dto.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.controller.admin.config.dto
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-06
 */
@Schema(description = "管理后台 - 参数分页DTO")
@Data
public class ConfigPageDTO extends PageParam {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "参数名称", example = "系统名称")
    private String configName;

    @Schema(description = "参数键", example = "system_name")
    private String configKey;

    @Schema(description = "参数类型", example = "1")
    private String configType;

    @Schema(description = "状态：0-禁用 1-启用", example = "1")
    private String status;
}
