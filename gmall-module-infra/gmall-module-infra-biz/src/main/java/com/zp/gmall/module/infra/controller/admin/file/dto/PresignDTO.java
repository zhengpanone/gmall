package com.zp.gmall.module.infra.controller.admin.file.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * Author : zhengpanone
 * Date : 2025/4/10 13:05
 * Version : v1.0.0
 * Description:
 */
@Schema(description = "生成预签名URL请求参数")
@Data
public class PresignDTO {
    @Schema(description = "桶名", example = "bucket")
    private String bucket;

    @NotBlank(message = "对象键不能为空")
    @Schema(description = "S3对象键(路径)", example = "uploads/user123/profile.jpg")
    private String objectKey;

    @NotBlank(message = "内容类型不能为空")
    @Schema(description = "文件内容类型", example = "image/jpeg")
    private String contentType;

    @NotNull(message = "过期时间不能为空")
    @Positive(message = "过期时间必须为正数")
    @Schema(description = "URL有效时间(分钟)", example = "30")
    private Integer expiryTimeMinutes;
}
