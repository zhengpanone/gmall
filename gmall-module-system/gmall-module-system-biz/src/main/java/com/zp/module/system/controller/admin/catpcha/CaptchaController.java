package com.zp.module.system.controller.admin.catpcha;

import cn.hutool.core.util.StrUtil;
import com.xingyuv.captcha.model.common.ResponseModel;
import com.xingyuv.captcha.model.vo.CaptchaVO;
import com.xingyuv.captcha.service.CaptchaService;
import com.zp.framework.common.pojo.Result;
import com.zp.framework.common.utils.servlet.ServletUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : zhengpanone
 * Date : 2023/11/14 10:40
 * Version : v1.0.0
 * Description: 验证码
 */
@Tag(name = "管理后台-验证码")
@RestController("adminCaptchaController")
@RequestMapping("/system/captcha")
public class CaptchaController {
    @Resource
    private CaptchaService captchaService;

    @PostMapping("/getCaptcha")
    @Operation(summary = "获得验证码")
    @PermitAll
    // @OperationLog(enable=false) // 避免Post请求被记录操作日志
    public Result<?> getCaptcha(@RequestBody CaptchaVO data, HttpServletRequest request) {
        assert request.getRemoteHost() != null;
        data.setBrowserInfo(getRemoteId(request));
        ResponseModel responseModel = captchaService.get(data);
        if (responseModel == null || !responseModel.isSuccess()) {
            return Result.failed("获得验证码失败");
        }
        return Result.ok(responseModel.getRepData());
    }

    public static String getRemoteId(HttpServletRequest request) {
        String ip = ServletUtils.getClientIP(request);
        String ua = request.getHeader("User-Agent");
        if (StrUtil.isNotBlank(ip)) {
            return ip + ua;
        }
        return request.getRemoteAddr() + ua;
    }
}
