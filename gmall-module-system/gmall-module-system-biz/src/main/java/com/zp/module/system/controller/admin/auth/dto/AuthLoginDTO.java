package com.zp.module.system.controller.admin.auth.dto;

import com.zp.framework.common.validation.InEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


/**
 * Author : zhengpanone
 * Date : 2023/11/11 13:15
 * Version : v1.0.0

 */
@Schema(description = "管理后台 - 账号密码登录")
@Data
public class AuthLoginDTO {
    @Schema(description = "账号", required = true, example = "admin")
    @NotEmpty(message = "登录账号不能为空")
    @Length(min = 4, max = 16, message = "账号长度为 4-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;

    @Schema(description = "密码", required = true, example = "admin")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    // ========== 图片验证码相关 ==========

    @Schema(description = "验证码，验证码开启时，需要传递", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "PfcH6mgr8tpXuMWFjvW6YVaqrswIuwmWI5dsVZSg7sGpWtDCUbHuDEXl3cFB1+VvCC/rAkSwK8Fad52FSuncVg==")
    @NotEmpty(message = "验证码不能为空", groups = CodeEnableGroup.class)
    private String captchaVerification;

    // ========== 绑定社交登录时，需要传递如下参数 ==========
    @Schema(description = "社交平台的类型,参见SocialTypeEnum枚举值", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    // @InEnum(SocialTypeEnum.class)
    private Integer socialType;

    /**
     * 开启验证码的Group
     */
    public interface CodeEnableGroup {
    }

}
