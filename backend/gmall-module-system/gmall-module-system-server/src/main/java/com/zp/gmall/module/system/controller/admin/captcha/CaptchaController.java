package com.zp.gmall.module.system.controller.admin.captcha;

import com.xingyuv.captcha.model.common.ResponseModel;
import com.xingyuv.captcha.model.vo.CaptchaVO;
import com.xingyuv.captcha.service.CaptchaService;
import com.zp.gmall.framework.common.domain.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin - Captcha")
@RestController
@RequestMapping("/captcha")
@RequiredArgsConstructor
public class CaptchaController {

    private final CaptchaService captchaService;

    @PostMapping("/get")
    @Operation(summary = "Get captcha")
    public Result<ResponseModel> get(@RequestBody CaptchaVO captchaVO) {
        return Result.ok(captchaService.get(captchaVO));
    }

    @PostMapping("/check")
    @Operation(summary = "Check captcha")
    public Result<ResponseModel> check(@RequestBody CaptchaVO captchaVO) {
        return Result.ok(captchaService.check(captchaVO));
    }
}
