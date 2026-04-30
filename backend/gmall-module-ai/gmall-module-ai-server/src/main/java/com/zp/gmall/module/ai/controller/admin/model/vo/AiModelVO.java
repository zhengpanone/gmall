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
@Schema(title = "AI模型VO", description = "管理后台 - AI模型VO")
public class AiModelVO {

    @Schema(title = "模型ID", description = "模型ID", example = "1")
    private String id;

    @Schema(title = "密钥ID", description = "密钥ID", example = "1")
    private String keyId;

    @Schema(title = "模型名称", description = "模型名称", example = "测试模型")
    private String name;

    @Schema(title = "模型", description = "模型", example = "测试模型")
    private String model;

    @Schema(title = "平台", description = "平台", example = "1")
    private String platform;

    @Schema(title = "类型", description = "类型", example = "1")
    private String type;

    @Schema(title = "排序", description = "排序", example = "1")
    private String sort;

    @Schema(title = "状态", description = "状态", example = "1")
    private String status;

    @Schema(title = "温度", description = "温度", example = "1.0")
    private Double temperature;

    @Schema(title = "最大令牌数", description = "最大令牌数", example = "1")
    private Integer maxTokens;

    @Schema(title = "最大消息数量", description = "最大消息数量", example = "1")
    private Integer maxContexts;

    @Schema(title = "创建时间", description = "创建时间", example = "2025-04-30 12:12:12")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    @Schema(title = "更新时间", description = "更新时间", example = "2025-04-30 12:12:12")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;
}
