package com.zp.gmall.module.system.controller.admin.permission;

import com.zp.gmall.framework.common.pojo.Ids;
import com.zp.gmall.framework.common.pojo.Result;
import com.zp.gmall.module.system.controller.admin.permission.dto.RoleSaveDTO;
import com.zp.gmall.module.system.controller.admin.permission.dto.RoleUpdateDTO;
import com.zp.gmall.module.system.service.permission.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhengpanone
 * Date : 2026/4/24 23:37
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "管理后台 - 角色")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@Validated
public class RoleController {

    @Resource
    private final IRoleService roleService;

    @PostMapping("/create")
    @Operation(summary = "新增角色")
    public Result<String> createRole(@RequestBody @Valid RoleSaveDTO roleSaveDTO) {
        String roleId = roleService.createRole(roleSaveDTO);
        return Result.ok(roleId);
    }

    @PostMapping("/update")
    @Operation(summary = "更新角色")
    public Result<String> updateRole(@RequestBody @Valid RoleUpdateDTO roleUpdateDTO) {
        String roleId = roleService.updateRole(roleUpdateDTO);
        return Result.ok(roleId);
    }

    @PostMapping("/delete")
    @Operation(summary = "删除角色")
    public Result<Void> deleteRole(@RequestBody @Valid Ids ids) {
        roleService.deleteRole(ids);
        return Result.ok();
    }
}
