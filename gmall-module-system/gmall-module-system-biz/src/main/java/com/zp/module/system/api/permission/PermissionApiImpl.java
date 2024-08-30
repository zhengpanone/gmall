package com.zp.module.system.api.permission;

import com.zp.framework.common.pojo.Result;
import com.zp.module.system.api.permission.dto.DeptDataPermissionVO;
import com.zp.module.system.service.permission.PermissionService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

/**
 * Author : zhengpanone
 * Date : 2024/7/30 18:38
 * Version : v1.0.0
 * Description: 提供 RESTful API 接口，给 Feign 调用
 */
@RestController
@Validated
public class PermissionApiImpl implements PermissionApi {
    @Resource
    private PermissionService permissionService;

    @Override
    public Result<Set<String>> getUserRoleIdListByRoleIds(Collection<String> roleIds) {
        return Result.ok(permissionService.getUserRoleIdListByRoleId(roleIds));
    }

    @Override
    public Result<Boolean> hasAnyPermissions(String userId, String... permissions) {
        return Result.ok(permissionService.hasAnyPermissions(userId, permissions));
    }

    @Override
    public Result<Boolean> hasAnyRoles(String userId, String... roles) {
        return Result.ok(permissionService.hasAnyRoles(userId, roles));
    }

    @Override
    public Result<DeptDataPermissionVO> getDeptDataPermission(String userId) {
        return Result.ok(permissionService.getDeptDataPermission(userId));
    }
}
