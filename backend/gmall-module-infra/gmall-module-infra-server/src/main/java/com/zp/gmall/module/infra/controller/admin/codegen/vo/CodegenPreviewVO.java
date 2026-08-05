package com.zp.gmall.module.infra.controller.admin.codegen.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "代码生成器预览")
@Data
public class CodegenPreviewVO {
    @Schema(description = "文件路径", requiredMode = Schema.RequiredMode.REQUIRED, example = "java/cn/iocoder/yudao/adminserver/modules/system/controller/test/SysTestDemoController.java")
    private String filePath;

    @Schema(description = "代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "Hello World")
    private String code;
}
