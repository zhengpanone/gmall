package com.zp.gmall.module.system.service.permission;

import java.util.Collection;
import java.util.Set;

/**
 * 权限服务
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2025-05-13
 * Description: 提供用户-角色、角色-菜单、角色-部门的关联权限处理
 */
public interface IPermissionService {

    /**
     * 根据用户id获取用户角色id列表
     * @param userId 用户id
     * @return 用户角色id列表
     */
    Set<String> getUserRoleIdListByUserId(String userId);


    Set<String> getRoleMenuIdListByRoleId(Collection<String> roleIds);

    Set<String> getRoleDeptIdListByRoleId(String roleId);
}
