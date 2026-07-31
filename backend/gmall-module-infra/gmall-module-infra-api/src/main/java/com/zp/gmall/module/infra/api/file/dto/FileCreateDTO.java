package com.zp.gmall.module.infra.api.file.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "文件创建请求DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileCreateDTO {
    @Schema(description = "文件内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "文件内容不能为空")
    private byte[] content;

    @Schema(description = "文件名称", example = "test.txt")
    private String name;

    @Schema(description = "文件目录", example = "test")
    private String directory;

    @Schema(description = "文件的MIME类型", example = "image/png")
    private String type;
}
