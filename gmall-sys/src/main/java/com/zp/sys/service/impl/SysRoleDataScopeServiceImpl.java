/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.zp.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zp.sys.model.entity.SysRoleDataScopeEntity;
import com.zp.sys.mapper.SysRoleDataScopeMapper;
import com.zp.sys.service.SysRoleDataScopeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色数据权限
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Service
public class SysRoleDataScopeServiceImpl extends ServiceImpl<SysRoleDataScopeMapper, SysRoleDataScopeEntity>
        implements SysRoleDataScopeService {

    @Override
    public List<Long> getDeptIdList(Long roleId) {
        return mapper.getDeptIdList(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
        //先删除角色数据权限关系
        deleteByRoleIds(new Long[]{roleId});

        //角色没有一个数据权限的情况
        if (CollUtil.isEmpty(deptIdList)) {
            return;
        }

        //保存角色数据权限关系
        for (Long deptId : deptIdList) {
            SysRoleDataScopeEntity sysRoleDataScopeEntity = new SysRoleDataScopeEntity();
            sysRoleDataScopeEntity.setDeptId(deptId);
            sysRoleDataScopeEntity.setRoleId(roleId);

            //保存
            save(sysRoleDataScopeEntity);
        }
    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        mapper.deleteByRoleIds(roleIds);
    }
}