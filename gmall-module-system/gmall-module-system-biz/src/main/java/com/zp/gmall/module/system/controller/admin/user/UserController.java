package com.zp.gmall.module.system.controller.admin.user;

import com.zp.gmall.framework.common.pojo.Result;
import com.zp.gmall.module.system.controller.admin.dto.UserSaveDTO;
import com.zp.gmall.module.system.controller.admin.dto.UserUpdateDTO;
import com.zp.gmall.module.system.controller.admin.user.vo.AdminUserVO;
import com.zp.gmall.module.system.service.user.IAdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/3/27 20:25
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "管理后台 - 用户")
@RestController
@RequestMapping("/system/user")
@Validated
public class UserController {
    @Resource
    private IAdminUserService adminUserService;

    @GetMapping("/test")
    public Result<?> test(){
        return Result.ok("system is start");
    }

    @PostMapping("/create")
    @Operation(summary = "新增用户")
    public Result<Long> createUser(UserSaveDTO userSaveDTO) {
        adminUserService.createUser(userSaveDTO);
        return Result.ok(1L);
    }

    public Result<?> updateUser(UserUpdateDTO userUpdateDTO) {
        adminUserService.updateUser(userUpdateDTO);
        return Result.ok();
    }

    @PostMapping("/list")
    @Operation(summary = "获取验证码")
    public Result<List<AdminUserVO>> getUserList() {
        List<AdminUserVO> userList = adminUserService.getUserListByIds(List.of("1"));
        return Result.ok(userList);
    }
}
