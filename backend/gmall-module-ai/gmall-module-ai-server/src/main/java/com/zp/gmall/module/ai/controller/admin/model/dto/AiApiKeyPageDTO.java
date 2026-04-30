package com.zp.gmall.module.ai.controller.admin.model.dto;

import com.zp.gmall.framework.common.domain.dto.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.ai.controller.admin.model.dto
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */
@Schema(name = "AI API密钥分页查询DTO", description = "管理后台 - AI API密钥分页查询DTO")
@Data
public class AiApiKeyPageDTO extends PageParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "API密钥名称")
    private String apiKeyName;

    @Schema(description = "API密钥类型")
    private Integer apiKeyType;

    @Schema(description = "API密钥状态")
    private Integer apiKeyStatus;

    @Schema(description = "API密钥描述")
    private String apiKeyDesc;

}
