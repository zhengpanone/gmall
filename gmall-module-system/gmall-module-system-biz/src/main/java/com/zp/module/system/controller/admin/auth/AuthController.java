package com.zp.module.system.controller.admin.auth;


import cn.hutool.core.util.StrUtil;
import com.zp.framework.common.pojo.Result;
import com.zp.framework.security.config.SecurityProperties;
import com.zp.framework.security.core.util.SecurityFrameworkUtils;
import com.zp.module.system.enums.logger.LoginLogTypeEnum;
import com.zp.module.system.controller.admin.auth.dto.AuthLoginDTO;
import com.zp.module.system.controller.admin.auth.vo.AuthLoginVO;
import com.zp.module.system.service.auth.AdminAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    private SecurityProperties securityProperties;

    @PostMapping("/login")
    @Operation(summary = "使用账号密码登录")
    @PermitAll
    public Result<AuthLoginVO> login(@RequestBody @Valid AuthLoginDTO authLoginDTO) {
        return Result.ok(authService.login(authLoginDTO));
    }

    @PostMapping("/logout")
    @PermitAll
    @Operation(summary = "登出系统")
    public Result<Boolean> logout(HttpServletRequest request) {
        String token = SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getTokenHeader(), securityProperties.getTokenParameter());
        if (StrUtil.isNotBlank(token)) {
            authService.logout(token, LoginLogTypeEnum.LOGOUT_SELF.getType());
        }
        return Result.ok(true);
    }
}
