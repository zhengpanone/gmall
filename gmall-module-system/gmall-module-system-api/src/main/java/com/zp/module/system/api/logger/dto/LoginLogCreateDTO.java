package com.zp.module.system.api.logger.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Author : zhengpanone
 * Date : 2023/11/13 23:03
 * Version : v1.0.0
 * Description: 登录日志创建DTO
 */
@Schema(description = "RPC 服务 - 登录日志创建 Request DTO")
@Data
public class LoginLogCreateDTO {
    @Schema(description = "日志类型,参见LoginLogTypeEnum枚举类", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "日志类型不能为空")
    private Integer logType;

    @Schema(description = "链路追踪编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "89aca178-a370-411c-ae02-3f0d672be4ab")
    private String traceId;

    @Schema(description = "用户编号", example = "666")
    private String userId;

    @Schema(description = "用户类型,参见UserTypeEnum枚举", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    @Schema(description = "用户账号", requiredMode = Schema.RequiredMode.REQUIRED, example = "admin")
    @NotNull(message = "用户账号不能为空")
    @Size(max = 30, message = "用户账号长度不能超过30个字符")
    private String username;

    @Schema(description = "登录结果,参见LoginResultEnum枚举类", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "登录结果不能为空")
    private Integer result;

    @Schema(description = "用户IP", requiredMode = Schema.RequiredMode.REQUIRED, example = "127.0.0.1")
    @NotEmpty(message = "用户IP不能为空")
    private String userIp;

    @Schema(description = "浏览器UserAgent", requiredMode = Schema.RequiredMode.REQUIRED, example = "Mozilla/5.0")
    private String userAgent;
}
