package com.zp.gmall.module.system.controller.admin.config.vo;

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
 * Module: com.zp.gmall.module.system.controller.admin.config.vo
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-06
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "管理后台 - 参数VO")
@Data
public class ConfigVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "参数ID", example = "1")
    private String id;

    @Schema(description = "参数名称", example = "系统名称")
    private String configName;

    @Schema(description = "参数键", example = "system_name")
    private String configKey;

    @Schema(description = "参数值", example = "GMall")
    private String configValue;

    @Schema(description = "参数类型", example = "1")
    private String configType;

    @Schema(description = "备注", example = "系统名称")
    private String remark;

    @Schema(description = "排序", example = "1")
    private Integer sort;

    @Schema(description = "状态：0-禁用 1-启用", example = "1")
    private Integer status;

    @Schema(description = "创建时间", example = "2026-05-06 12:00:00")
    private LocalDateTime createTime;
}
