package com.zp.gmall.module.system.service.impl;

import com.zp.gmall.framework.common.pojo.PageResult;
import com.zp.gmall.module.system.controller.admin.dto.RolePageDTO;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import com.zp.gmall.module.system.mapper.permission.RoleMapper;
import com.zp.gmall.module.system.service.IRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Author : zhengpanone
 * Date : 2025/3/28 14:33
 * Version : v1.0.0
 * Description:
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Resource
    RoleMapper roleMapper;

    @Override
    public PageResult<RoleDO> getRolePage(RolePageDTO reqVO) {
        return roleMapper.selectPage(reqVO);
    }


}
