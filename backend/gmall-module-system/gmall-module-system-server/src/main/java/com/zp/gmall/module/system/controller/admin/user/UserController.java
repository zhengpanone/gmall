package com.zp.gmall.module.system.controller.admin.user;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.framework.validation.group.CreateGroup;
import com.zp.gmall.framework.validation.group.UpdateGroup;
import com.zp.gmall.module.system.controller.admin.user.dto.UserDTO;
import com.zp.gmall.module.system.controller.admin.user.vo.AdminUserVO;
import com.zp.gmall.module.system.service.user.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/3/27 20:25
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "管理后台 - 用户")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Resource
    private IUserService adminUserService;

    @Operation(summary = "根据ID获取用户")
    @GetMapping("/{id}")
    public Result<?> getById(
            @Parameter(description = "用户ID", required = true, example = "1")
            @PathVariable String id) {
        return Result.ok(adminUserService.getUserById(id));
    }

    @PostMapping("/create")
    @Operation(summary = "新增用户")
    public Result<String> create(@RequestBody @Validated(CreateGroup.class) UserDTO userDTO) {
        String userId = adminUserService.createUser(userDTO);
        return Result.ok(userId);
    }

    @PutMapping("/update")
    @Operation(summary = "根据ID更新用户")
    public Result<?> update(@RequestBody @Validated(UpdateGroup.class) UserDTO userDTO) {
        adminUserService.updateUser(userDTO);
        return Result.ok();
    }

    @PostMapping("/list")
    @Operation(summary = "获取验证码")
    public Result<List<AdminUserVO>> getList() {
        List<AdminUserVO> userList = adminUserService.getUserListByIds(List.of("1"));
        return Result.ok(userList);
    }
}
