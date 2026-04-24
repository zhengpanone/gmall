package com.zp.gmall.module.system.controller.admin.captcha;

import com.zp.gmall.framework.common.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : zhengpanone
 * Date : 2025/3/27 13:05
 * Version : v1.0.0
 * Description:
 */
@Component
@Tag(name = "管理后台 - 验证码")
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @PostMapping("/getCaptcha")
    @Operation(summary = "获取验证码")
    public Result<?> getCaptcha() {
        return Result.ok();
    }
}
