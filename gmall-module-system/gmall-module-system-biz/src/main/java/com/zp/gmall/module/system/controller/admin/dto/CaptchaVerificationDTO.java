package com.zp.gmall.module.system.controller.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 18:39
 * Version : v1.0.0
 * Description:
 */
@Schema(description = "管理后台 - 验证码 DTO")
public class CaptchaVerificationDTO {

    // ========== 图片验证码相关 ==========
    @Schema(description = "验证码，验证码开启时，需要传递", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "PfcH6mgr8tpXuMWFjvW6YVaqrswIuwmWI5dsVZSg7sGpWtDCUbHuDEXl3cFB1+VvCC/rAkSwK8Fad52FSuncVg==")
    @NotEmpty(message = "验证码不能为空", groups = CodeEnableGroup.class)
    private String captchaVerification;

    /**
     * 开启验证码的 Group
     */
    public interface CodeEnableGroup {
    }
}
