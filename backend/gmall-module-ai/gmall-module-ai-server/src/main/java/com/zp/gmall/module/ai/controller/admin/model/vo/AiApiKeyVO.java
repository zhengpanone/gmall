package com.zp.gmall.module.ai.controller.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.ai.controller.admin.model.vo
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "AI API密钥VO", description = "管理后台 - AI API密钥VO")
public class AiApiKeyVO {

    @Schema(title = "API密钥ID", description = "API密钥ID", example = "1")
    private String id;

    @Schema(title = "API密钥名称", description = "API密钥名称", example = "测试密钥")
    private String name;

    @Schema(title = "API密钥", description = "API密钥", example = "<KEY>")
    private String apiKey;

    @Schema(title = "平台", description = "平台", example = "1")
    private String platform;

    @Schema(title = "URL", description = "URL", example = "https://www.baidu.com")
    private String url;

    @Schema(title = "状态", description = "状态", example = "1")
    private String status;

    @Schema(title = "备注", description = "备注", example = "测试备注")
    private String remark;

    @Schema(title = "创建时间", description = "创建时间", example = "2025-04-30 12:12:12")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    @Schema(title = "更新时间", description = "更新时间", example = "2025-04-30 12:12:12")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;
}
