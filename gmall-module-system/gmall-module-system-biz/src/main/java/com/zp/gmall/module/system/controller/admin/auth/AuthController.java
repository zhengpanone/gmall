package com.zp.gmall.module.system.controller.admin.auth;

import com.zp.gmall.framework.common.pojo.Result;
import com.zp.gmall.module.system.controller.admin.auth.dto.AuthLoginDTO;
import com.zp.gmall.module.system.service.auth.IAdminAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zp.gmall.framework.common.pojo.Result.ok;

@Tag(name = "管理后台 - 认证")
@RestController
@RequestMapping("/system/auth")
@Slf4j
public class AuthController {
    @Resource
    private IAdminAuthService authService;

    @PostMapping("/login")
    @Operation(summary = "使用账号密码登录")
    public Result<?> login(@RequestBody AuthLoginDTO authLoginDTO) {
        return ok(authService.login(authLoginDTO));
    }

}