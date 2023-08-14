/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.zp.sys.service;

import com.mybatisflex.core.service.IService;
import com.zp.common.security.user.UserDetail;
import com.zp.sys.dto.SysMenuDTO;
import com.zp.sys.entity.SysMenuEntity;

import java.util.List;


/**
 * 菜单管理
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    SysMenuDTO get(Long id);

    void save(SysMenuDTO dto);

    void update(SysMenuDTO dto);

    void delete(Long id);

    /**
     * 菜单列表
     *
     * @param menuType 菜单类型
     */
    List<SysMenuDTO> getAllMenuList(Integer menuType);

    /**
     * 用户菜单列表
     *
     * @param user     用户
     * @param menuType 菜单类型
     */
    List<SysMenuDTO> getUserMenuList(UserDetail user, Integer menuType);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param pid 父菜单ID
     */
    List<SysMenuDTO> getListPid(Long pid);
}
