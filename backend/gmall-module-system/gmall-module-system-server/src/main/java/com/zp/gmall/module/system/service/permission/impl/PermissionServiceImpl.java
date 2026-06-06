package com.zp.gmall.module.system.service.permission.impl;

import cn.hutool.core.collection.CollUtil;
import com.zp.gmall.module.system.entity.permission.MenuDO;
import com.zp.gmall.module.system.entity.permission.RoleMenuDO;
import com.zp.gmall.module.system.entity.permission.UserRoleDO;
import com.zp.gmall.module.system.mapper.permission.MenuMapper;
import com.zp.gmall.module.system.mapper.permission.RoleMenuMapper;
import com.zp.gmall.module.system.mapper.permission.UserRoleMapper;
import com.zp.gmall.module.system.service.permission.IPermissionService;
import com.zp.gmall.module.system.service.permission.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static com.zp.gmall.framework.common.util.collection.CollectionUtils.convertSet;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements IPermissionService {

    private final UserRoleMapper userRoleMapper;

    private final RoleMenuMapper roleMenuMapper;

    private final MenuMapper menuMapper;

    private final IRoleService roleService;


    @Override
    public Set<String> getUserRoleIdListByUserId(String userId) {
        return convertSet(userRoleMapper.getListByUserId(userId), UserRoleDO::getRoleId);
    }

    @Override
    public Set<String> getRoleMenuIdListByRoleId(Collection<String> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return Collections.emptySet();
        }
        if (roleService.hasAnySuperAdmin(roleIds)) {
            return convertSet(menuMapper.selectList(), MenuDO::getId);
        }
        return convertSet(roleMenuMapper.selectList(RoleMenuDO::getRoleId, roleIds), RoleMenuDO::getMenuId);
    }

    @Override
    public Set<String> getRoleDeptIdListByRoleId(String roleId) {
        return Set.of();
    }
}
