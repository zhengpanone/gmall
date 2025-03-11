package com.zp.gmall.module.infra.controller.admin.file.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * Author : zhengpanone
 * Date : 2025/4/10 13:42
 * Version : v1.0.0
 * Description:
 */
@Data
@Schema(description = "预签名URL响应")
public class PresignVO {

    @Schema(description = "预签名URL", example = "https://your-bucket.s3.amazonaws.com/uploads/test.jpg?X-Amz-Algorithm=...")
    private String url;

    @Schema(description = "HTTP方法", example = "PUT")
    private String method;

    @Schema(description = "过期时间")
    private Date expiresAt;

    @Schema(description = "需要附加的HTTP头")
    private Map<String, String> headers;
}
