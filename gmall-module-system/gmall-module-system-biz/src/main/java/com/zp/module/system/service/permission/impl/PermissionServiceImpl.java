package com.zp.module.system.service.permission.impl;

import com.zp.module.system.api.permission.dto.DeptDataPermissionRespDTO;
import com.zp.module.system.service.permission.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * Author : zhengpanone
 * Date : 2024/7/30 18:44
 * Version : v1.0.0
 * Description: TODO
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {


    /**
     * 判断是否有权限，任一一个即可
     *
     * @param userId      用户编号
     * @param permissions 权限
     * @return 是否
     */
    @Override
    public boolean hasAnyPermissions(String userId, String... permissions) {
        return false;
    }

    /**
     * 判断是否有角色，任一一个即可
     *
     * @param userId 用户编号
     * @param roles  角色数组
     * @return 是否
     */
    @Override
    public boolean hasAnyRoles(String userId, String... roles) {
        return false;
    }

    /**
     * 设置角色菜单
     *
     * @param roleId  角色编号
     * @param menuIds 菜单编号集合
     */
    @Override
    public void assignRoleMenu(String roleId, Set<String> menuIds) {

    }

    /**
     * 处理角色删除时，删除关联授权数据
     *
     * @param roleId 角色编号
     */
    @Override
    public void processRoleDeleted(String roleId) {

    }

    /**
     * 处理菜单删除时，删除关联授权数据
     *
     * @param menuId 菜单编号
     */
    @Override
    public void processMenuDeleted(String menuId) {

    }

    /**
     * 获得角色拥有的菜单编号集合
     *
     * @param roleId 角色编号
     * @return 菜单编号集合
     */
    @Override
    public Set<String> getRoleMenuListByRoleId(String roleId) {
        return PermissionService.super.getRoleMenuListByRoleId(roleId);
    }

    /**
     * 获得角色们拥有的菜单编号集合
     *
     * @param roleIds 角色编号数组
     * @return 菜单编号集合
     */
    @Override
    public Set<String> getRoleMenuListByRoleId(Collection<String> roleIds) {
        return Set.of();
    }

    /**
     * 获得拥有指定菜单的角色编号数组，从缓存中获取
     *
     * @param menuId 菜单编号
     * @return 角色编号数组
     */
    @Override
    public Set<String> getMenuRoleIdListByMenuIdFromCache(String menuId) {
        return Set.of();
    }

    /**
     * 设置用户角色
     *
     * @param userId  角色编号
     * @param roleIds 角色编号集合
     */
    @Override
    public void assignUserRole(String userId, Set<String> roleIds) {

    }

    /**
     * 处理用户删除时，删除关联授权数据
     *
     * @param userId 用户编号
     */
    @Override
    public void processUserDeleted(String userId) {

    }

    /**
     * 获得拥有多个角色的用户编号集合
     *
     * @param roleIds 角色编号集合
     * @return 用户编号集合
     */
    @Override
    public Set<String> getUserRoleIdListByRoleId(Collection<String> roleIds) {
        return Set.of();
    }

    /**
     * 获得用户拥有的角色编号集合
     *
     * @param userId 用户编号
     * @return 角色编号集合
     */
    @Override
    public Set<String> getUserRoleIdListByUserId(String userId) {
        return Set.of();
    }

    /**
     * 获得用户拥有的角色编号集合，从缓存中获取
     *
     * @param userId 用户编号
     * @return 角色编号集合
     */
    @Override
    public Set<String> getUserRoleIdListByUserIdFromCache(String userId) {
        return Set.of();
    }

    /**
     * 设置角色的数据权限
     *
     * @param roleId           角色编号
     * @param dataScope        数据范围
     * @param dataScopeDeptIds 部门编号数组
     */
    @Override
    public void assignRoleDataScope(String roleId, Integer dataScope, Set<String> dataScopeDeptIds) {

    }

    /**
     * 获得登陆用户的部门数据权限
     *
     * @param userId 用户编号
     * @return 部门数据权限
     */
    @Override
    public DeptDataPermissionRespDTO getDeptDataPermission(String userId) {
        return null;
    }
}
