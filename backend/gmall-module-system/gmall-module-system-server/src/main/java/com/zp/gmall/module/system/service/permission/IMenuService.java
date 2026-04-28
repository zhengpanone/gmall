package com.zp.gmall.module.system.service.permission;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.vo.TreeSelectVO;
import com.zp.gmall.module.system.controller.admin.permission.dto.MenuDTO;
import com.zp.gmall.module.system.controller.admin.permission.vo.MenuTreeVO;
import com.zp.gmall.module.system.controller.admin.permission.vo.MenuVO;
import com.zp.gmall.module.system.controller.admin.permission.vo.RouteVO;
import com.zp.gmall.module.system.entity.permission.MenuDO;

import java.util.List;

/**
 * @author : zhengpanone
 * Date : 2026/4/25 21:43
 * Version : v1.0.0
 * Description:
 */
public interface IMenuService extends IService<MenuDO> {

    List<MenuVO> getMenuByUserId(String userId);

    List<String> getPermsByUserId(String userId);

    List<RouteVO> getRoutesByUserId(String userId);

    /**
     * 构建前端路由所需要的菜单
     */
    List<RouteVO> buildMenus(List<MenuTreeVO> menus);

    /**
     * 构建前端所需树结构
     */
    List<MenuTreeVO> buildMenuTree(List<MenuTreeVO> menus);

    /**
     * 构建前端所需要树结构
     */
    List<TreeSelectVO> buildMenuTreeSelect(List<MenuTreeVO> menus);

    /**
     * 根据角色ID查询菜单树信息
     */
    List<Long> getMenuListByRoleId(Long roleId);

    /**
     * 新增菜单
     */
    void addMenu(MenuDTO dto);

    /**
     * 修改菜单
     */
    void updateMenu(MenuDTO dto);

    /**
     * 删除菜单
     */
    void deleteMenu(String menuId);

    /**
     * 校验菜单名称是否唯一
     */
    boolean checkMenuNameUnique(MenuDTO dto);

    /**
     * 校验菜单标识是否唯一
     */
    boolean checkMenuKeyUnique(MenuDTO dto);

    /**
     * 是否存在菜单子节点
     */
    boolean hasChildByMenuId(Long menuId);

    /**
     * 查询菜单是否存在角色
     */
    boolean checkMenuExistRole(Long menuId);

    /**
     * 获取菜单树
     */
    List<MenuTreeVO> getMenuTree(MenuDTO dto);

    /**
     * 获取菜单下拉树列表
     */
    List<TreeSelectVO> getMenuTreeSelect();

    /**
     * 根据菜单ID获取详细信息
     */
    MenuDTO getMenuById(Long menuId);
}
