package com.zp.gmall.module.system.service;

import com.zp.gmall.framework.common.pojo.PageResult;
import com.zp.gmall.module.system.controller.admin.dto.RolePageDTO;
import com.zp.gmall.module.system.entity.permission.RoleDO;

/**
 * Author : zhengpanone
 * Date : 2025/3/28 14:33
 * Version : v1.0.0
 * Description:
 */
public interface IRoleService {

    public PageResult<RoleDO> getRolePage(RolePageDTO reqVO);
}
