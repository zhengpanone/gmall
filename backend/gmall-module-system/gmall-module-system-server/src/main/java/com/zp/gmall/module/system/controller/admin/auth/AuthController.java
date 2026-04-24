package com.zp.gmall.module.system.controller.admin.auth;

import com.zp.gmall.framework.common.pojo.Result;
import com.zp.gmall.module.system.controller.admin.auth.dto.AuthLoginDTO;
import com.zp.gmall.module.system.service.auth.IAdminAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zp.gmall.framework.common.pojo.Result.ok;

/**
 * 使用 @RequiredArgsConstructor + final @NonNull field 实现构造器注入Bean
 * @RequiredArgsConstructor 这个属性是Lombok依赖提供的，作用域在类上，作用是生成所需要参数的构造函数，但是注意：字段必须是final修饰和具有@NonNull等约束的字段（这里的 final 和 @NonNull 满足其一即可）
 *
 * @AllArgsConstructor：生成该类下全部属性的构造方法。
 * @RequiredArgsConstructor：生成该类下被final修饰或者带有@NonNull的构造方法。
 *
 * 使用@AllArgsConstructor之后，@Value就会不起作用
 * 可以使用@RequiredArgsConstructor代替
 *
 */

@Tag(name = "管理后台 - 认证")
@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final @NotNull IAdminAuthService authService;

    @PostMapping("/login")
    @Operation(summary = "使用账号密码登录")
    public Result<?> login(@RequestBody AuthLoginDTO authLoginDTO) {
        return ok(authService.login(authLoginDTO));
    }

}