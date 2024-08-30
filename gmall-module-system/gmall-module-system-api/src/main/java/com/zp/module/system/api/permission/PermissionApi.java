package com.zp.module.system.api.permission;

import com.zp.framework.common.pojo.Result;
import com.zp.module.system.api.permission.dto.DeptDataPermissionVO;
import com.zp.module.system.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Set;

/**
 * Author : zhengpanone
 * Date : 2024/7/30 18:30
 * Version : v1.0.0
 * Description: TODO
 */
@FeignClient(name = ApiConstants.NAME)
@Tag(name = "RPC 服务-权限")
public interface PermissionApi {
    String PREFIX = ApiConstants.PREFIX + "/permission";

    @GetMapping(PREFIX + "/user-role-id-list-by-role-id")
    @Operation(summary = "获得拥有多个角色的用户编号集合")
    @Parameter(name = "roleIds", description = "角色编号集合", example = "1,2", required = true)
    Result<Set<String>> getUserRoleIdListByRoleIds(@RequestParam("roleIds") Collection<String> roleIds);

    @GetMapping(PREFIX + "/has-any-permissions")
    @Operation(summary = "判断是否有权限，任一一个即可")
    @Parameters({
            @Parameter(name = "userId", description = "用户编号", example = "1", required = true),
            @Parameter(name = "permissions", description = "权限", example = "read,write", required = true)
    })
    Result<Boolean> hasAnyPermissions(@RequestParam("userId") String userId,
                                      @RequestParam("permissions") String... permissions);

    @GetMapping(PREFIX + "/has-any-roles")
    @Operation(summary = "判断是否有角色，任一一个即可")
    @Parameters({
            @Parameter(name = "userId", description = "用户编号", example = "1", required = true),
            @Parameter(name = "roles", description = "角色数组", example = "2", required = true)
    })
    Result<Boolean> hasAnyRoles(@RequestParam("userId") String userId,
                                @RequestParam("roles") String... roles);

    @GetMapping(PREFIX + "/get-dept-data-permission")
    @Operation(summary = "获得登陆用户的部门数据权限")
    @Parameter(name = "userId", description = "用户编号", example = "2", required = true)
    Result<DeptDataPermissionVO> getDeptDataPermission(@RequestParam("userId") String userId);
}
