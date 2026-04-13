package com.zp.gmall.module.system.controller.admin.auth.dto;


import com.zp.gmall.module.system.controller.admin.captcha.dto.CaptchaVerificationDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 18:38
 * Version : v1.0.0
 * Description:
 */
@Schema(description = "管理后台 - 账号密码登录 DTO，如果登录并绑定社交用户，需要传递 social 开头的参数")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthLoginDTO extends CaptchaVerificationDTO {

    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudaoyuanma")
    @NotEmpty(message = "登录账号不能为空")
    //@Length(min = 4, max = 16, message = "账号长度为 4-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "buzhidao")
    @NotEmpty(message = "密码不能为空")
    //@Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;
}
