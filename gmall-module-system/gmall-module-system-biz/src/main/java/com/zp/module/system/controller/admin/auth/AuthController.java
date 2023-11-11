package com.zp.module.system.controller.admin.auth;


import com.zp.framework.common.pojo.Result;
import com.zp.module.system.controller.admin.auth.dto.AuthLoginDTO;
import com.zp.module.system.controller.admin.auth.vo.AuthLoginVO;
import com.zp.module.system.service.auth.AdminAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : zhengpanone
 * Date : 2023/11/11 15:54
 * Version : v1.0.0
 
 */
@Tag(name = "管理后台-认证")
@RestController
@RequestMapping("/system/auth")
@Validated
@Slf4j
public class AuthController {
    @Resource
    private AdminAuthService authService;

    @PostMapping("/login")
    @Operation(summary = "使用账号密码登录")
    // @OperateLog()
    public Result<AuthLoginVO> login(@RequestBody @Valid AuthLoginDTO authLoginDTO) {
        return Result.ok(authService.login(authLoginDTO));
    }
}
