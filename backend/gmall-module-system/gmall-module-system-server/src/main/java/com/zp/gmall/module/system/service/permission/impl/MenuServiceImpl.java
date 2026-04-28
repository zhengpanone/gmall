package com.zp.gmall.module.system.service.permission.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.vo.TreeSelectVO;
import com.zp.gmall.framework.common.exception.ServerException;
import com.zp.gmall.module.system.controller.admin.permission.dto.MenuDTO;
import com.zp.gmall.module.system.controller.admin.permission.vo.MenuTreeVO;
import com.zp.gmall.module.system.controller.admin.permission.vo.MenuVO;
import com.zp.gmall.module.system.controller.admin.permission.vo.RouteMetaVO;
import com.zp.gmall.module.system.controller.admin.permission.vo.RouteVO;
import com.zp.gmall.module.system.convert.permission.MenuConvertMapper;
import com.zp.gmall.module.system.entity.permission.MenuDO;
import com.zp.gmall.module.system.mapper.permission.MenuMapper;
import com.zp.gmall.module.system.mapper.permission.RoleMenuMapper;
import com.zp.gmall.module.system.service.permission.IMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuDO> implements IMenuService {

    private final RoleMenuMapper roleMenuMapper;

    private final MenuConvertMapper menuConvertMapper = Mappers.getMapper(MenuConvertMapper.class);

    @Override
    public List<MenuVO> getMenuByUserId(String userId) {
        // 管理员返回所有菜单
        List<MenuDO> list = new ArrayList<>();
        // 管理员
        if (true) {
            list = baseMapper.selectList(new QueryWrapper<MenuDO>()
                    .eq("visible", 1)
                    .eq("status", 1)
                    .orderByAsc("sort"));
        } else {
            // 普通用户查询角色关联的菜单
            list = baseMapper.selectMenuByUserId(userId);
        }
        return menuConvertMapper.convertList(list);
    }

    @Override
    public List<String> getPermsByUserId(String userId) {
        return baseMapper.selectPermsByUserId(userId);
    }


    @Override
    public List<RouteVO> getRoutesByUserId(String userId) {
        List<MenuVO> menus = getMenuByUserId(userId);

        return menus.stream()
                .filter(menu -> menu.getType() != 2)  // 过滤掉按钮
                .map(this::convertToRoute)
                .collect(Collectors.toList());
    }

    @Override
    public List<RouteVO> buildMenus(List<MenuTreeVO> menus) {
        return List.of();
    }

    @Override
    public List<MenuTreeVO> buildMenuTree(List<MenuTreeVO> menus) {
        return List.of();
    }

    @Override
    public List<TreeSelectVO> buildMenuTreeSelect(List<MenuTreeVO> menus) {
        return List.of();
    }

    @Override
    public List<Long> getMenuListByRoleId(Long roleId) {
        return List.of();
    }

    @Override
    public void addMenu(MenuDTO dto) {
        // 校验菜单标识唯一性
        if (!checkMenuKeyUnique(dto)) {
            throw new ServerException("新增菜单'" + dto.getName() + "'失败，菜单标识已存在");
        }


        MenuDO menuDO = menuConvertMapper.convert(dto);
        // 设置祖先ID
        if (StrUtil.isNotEmpty(menuDO.getParentId()) && !"0".equals(menuDO.getParentId())) {
            MenuDO parentMenu = getById(menuDO.getParentId());
            if (parentMenu == null) {
                throw new ServerException("父菜单不存在");
            }
            String ancestorIds = StrUtil.isNotEmpty(parentMenu.getAncestorIds())
                    ? parentMenu.getAncestorIds() + "," + parentMenu.getId()
                    : parentMenu.getId().toString();
            menuDO.setAncestorIds(ancestorIds);
        } else {
            menuDO.setAncestorIds("");
        }

        // 保存菜单
        if (!save(menuDO)) {
            throw new ServerException("新增菜单失败");
        }

        log.info("菜单新增成功，菜单ID：{}，菜单名称：{}", menuDO.getId(), menuDO.getName());
    }

    @Override
    public void updateMenu(MenuDTO dto) {

    }

    @Override
    public void deleteMenu(Long menuId) {

    }

    @Override
    public boolean checkMenuNameUnique(MenuDTO dto) {
        return false;
    }

    @Override
    public boolean checkMenuKeyUnique(MenuDTO dto) {
        String menuId = dto.getId();
        int count = baseMapper.checkMenuKeyUnique(dto.getCode(), menuId);
        return count == 0;

    }

    @Override
    public boolean hasChildByMenuId(Long menuId) {
        return false;
    }

    @Override
    public boolean checkMenuExistRole(Long menuId) {
        return false;
    }

    @Override
    public List<MenuTreeVO> getMenuTree(MenuDTO dto) {
        return List.of();
    }

    @Override
    public List<TreeSelectVO> getMenuTreeSelect() {
        // 查询所有菜单
        List<MenuDO> menuList = baseMapper.selectList(
                new QueryWrapper<MenuDO>()
                        .eq("status", 1)
                        .orderByAsc("sort")
        );

        // 转换为 MenuTreeVO
        List<MenuTreeVO> menuTreeVOS = menuConvertMapper.convertMenuTreeList(menuList);

        // 构建树形结构
        List<MenuTreeVO> menuTree = buildMenuTreeNode(menuTreeVOS, "0");

        // 转换为 TreeSelectVO
        return convertToTreeSelect(menuTree);
    }

    /**
     * 构建树形节点
     */
    private List<MenuTreeVO> buildMenuTreeNode(List<MenuTreeVO> menus, String parentId) {
        return menus.stream()
                .filter(menu -> parentId.equals(menu.getParentId()))
                .peek(menu -> menu.setChildren(buildMenuTreeNode(menus, menu.getId())))
                .collect(Collectors.toList());
    }

    /**
     * 转换为 TreeSelectVO
     */
    private List<TreeSelectVO> convertToTreeSelect(List<MenuTreeVO> menuTrees) {
        if (menuTrees == null || menuTrees.isEmpty()) {
            return List.of();
        }
        return menuTrees.stream().map(menu -> {
            TreeSelectVO vo = new TreeSelectVO();
            vo.setId(menu.getId());
            vo.setValue(menu.getName());
            vo.setLabel(menu.getName());
            vo.setParentId(menu.getParentId());
            vo.setMenuKey(menu.getCode());
            vo.setChildren(convertToTreeSelect(menu.getChildren()));
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public MenuDTO getMenuById(Long menuId) {
        return null;
    }

    private RouteVO convertToRoute(MenuVO menu) {
        RouteVO route = new RouteVO();
        route.setPath(menu.getPath());
        route.setName(menu.getName());
        route.setComponent(menu.getComponent());
        route.setMeta(RouteMetaVO.builder().build()
                .setTitle(menu.getTitle())
                .setIcon(menu.getIcon())
                .setHideMenu(menu.getVisible())
                .setAffix(menu.getAffix())
        );

        if (menu.getChildren() != null && !menu.getChildren().isEmpty()) {
            List<RouteVO> children = menu.getChildren().stream()
                    .map(this::convertToRoute)
                    .collect(Collectors.toList());
            route.setChildren(children);
        }

        return route;
    }
}