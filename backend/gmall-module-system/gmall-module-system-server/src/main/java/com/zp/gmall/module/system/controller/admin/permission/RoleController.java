package com.zp.gmall.module.system.controller.admin.permission;

import com.fasterxml.jackson.annotation.JsonView;
import com.zp.gmall.framework.common.domain.ViewGroup;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.framework.common.validation.ValidateGroup.Create;
import com.zp.gmall.framework.common.validation.ValidateGroup.Update;
import com.zp.gmall.module.system.controller.admin.permission.dto.RoleDTO;
import com.zp.gmall.module.system.controller.admin.permission.dto.RolePageDTO;
import com.zp.gmall.module.system.controller.admin.permission.vo.RoleVO;
import com.zp.gmall.module.system.service.permission.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @JsonView(ViewGroup.CreateView.class)
    public Result<String> createRole(@RequestBody @Validated(Create.class) @Valid RoleDTO roleDTO) {
        String roleId = roleService.createRole(roleDTO);
        return Result.ok(roleId);
    }

    @PutMapping("/update")
    @Operation(summary = "更新角色")
    @JsonView(ViewGroup.UpdateView.class)
    public Result<String> updateRole(@RequestBody @Validated(Update.class) @Valid RoleDTO roleDTO) {
        String roleId = roleService.updateRole(roleDTO);
        return Result.ok(roleId);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除角色")
    public Result<Void> deleteRole(@RequestBody @Valid Ids ids) {
        roleService.deleteRole(ids);
        return Result.ok();
    }

    @GetMapping("/page")
    @Operation(summary = "获取角色分页")
    public PageResult<RoleVO> getRolePage(@Valid RolePageDTO rolePageDTO) {
        return roleService.getRolePage(rolePageDTO);
    }

    @GetMapping("/get")
    @Operation(summary = "获取角色详情")
    public Result<RoleVO> getRoleById(
            @Parameter(description = "角色ID", required = true, example = "1")
            @RequestParam("id") String id) {
        return Result.ok(roleService.getRoleById(id));
    }
}
