package com.zp.module.infra.controller.admin.db.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Author : zhengpanone
 * Date : 2024/7/8 20:57
 * Version : v1.0.0
 * Description: 数据源配置创建/修改 Request DTO
 */
@Schema(description = "管理后台 - 数据源配置创建/修改 Request VO")
@Data
public class DataSourceConfigSaveReqDTO {
    @Schema(description = "主键编号", example = "1024")
    private String id;

    @Schema(description = "数据源名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "test")
    @NotNull(message = "数据源名称不能为空")
    private String name;

    @Schema(description = "数据源连接", requiredMode = Schema.RequiredMode.REQUIRED, example = "jdbc:mysql://127.0.0.1:3306/ruoyi-vue-pro")
    @NotNull(message = "数据源连接不能为空")
    private String url;

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "root")
    @NotNull(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    @NotNull(message = "密码不能为空")
    private String password;
}
