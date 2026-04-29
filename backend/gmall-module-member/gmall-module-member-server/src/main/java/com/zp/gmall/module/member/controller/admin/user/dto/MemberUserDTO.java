package com.zp.gmall.module.member.controller.admin.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:06
 * Version : v1.0.0
 * Description:
 */
@Schema(description = "管理后台 - 会员用户更新 Request VO")
@Data
public class MemberUserDTO {
    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "23788")
    @NotNull(message = "编号不能为空")
    private String id;
}
