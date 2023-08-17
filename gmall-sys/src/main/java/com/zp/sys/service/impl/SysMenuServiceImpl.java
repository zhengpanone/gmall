/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.zp.sys.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zp.common.constant.Constant;
import com.zp.common.exception.BusinessException;
import com.zp.common.exception.ErrorCode;
import com.zp.common.security.user.UserDetail;
import com.zp.common.utils.ConvertUtils;
import com.zp.common.utils.TreeUtils;
import com.zp.sys.model.dto.SysMenuDTO;
import com.zp.sys.model.entity.SysMenuEntity;
import com.zp.sys.model.enums.SuperAdminEnum;
import com.zp.sys.mapper.SysMenuMapper;
import com.zp.sys.service.SysMenuService;
import com.zp.sys.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public SysMenuDTO get(Long id) {
        SysMenuEntity entity = mapper.getById(id);

        SysMenuDTO dto = ConvertUtils.sourceToTarget(entity, SysMenuDTO.class);

        return dto;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysMenuDTO dto) {
        SysMenuEntity entity = ConvertUtils.sourceToTarget(dto, SysMenuEntity.class);
        //保存菜单
        save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysMenuDTO dto) {
        SysMenuEntity entity = ConvertUtils.sourceToTarget(dto, SysMenuEntity.class);

        //上级菜单不能为自身
        if (entity.getId().equals(entity.getPid())) {
            throw new BusinessException(ErrorCode.SUPERIOR_MENU_ERROR);
        }

        //更新菜单
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        //删除菜单
        removeById(id);

        //删除角色菜单关系
        sysRoleMenuService.deleteByMenuId(id);
    }

    @Override
    public List<SysMenuDTO> getAllMenuList(Integer menuType) {
        List<SysMenuEntity> menuList = mapper.getMenuList(menuType);

        List<SysMenuDTO> dtoList = ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);

        return TreeUtils.build(dtoList, Constant.MENU_ROOT);
    }


    @Override
    public List<SysMenuDTO> getUserMenuList(UserDetail user, Integer menuType) {
        List<SysMenuEntity> menuList;

        //系统管理员，拥有最高权限
        if (user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
            menuList = mapper.getMenuList(menuType);
        } else {
            menuList = mapper.getUserMenuList(user.getId(), menuType);
        }

        List<SysMenuDTO> dtoList = ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);

        return TreeUtils.build(dtoList);
    }

    @Override
    public List<SysMenuDTO> getListPid(Long pid) {
        List<SysMenuEntity> menuList = mapper.getListPid(pid);

        return ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);
    }

}