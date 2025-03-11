package com.zp.gmall.module.system.mapper.permission;

import com.zp.gmall.framework.common.pojo.PageResult;
import com.zp.gmall.framework.mybatis.core.LambdaQueryWrapperX;
import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.module.system.controller.admin.dto.RolePageDTO;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author : zhengpanone
 * Date : 2025/3/28 13:45
 * Version : v1.0.0
 * Description:
 */
@Mapper
public interface RoleMapper extends BaseMapperX<RoleDO> {
    default PageResult<RoleDO> selectPage(RolePageDTO reqDTO) {
        return selectPage(reqDTO, new LambdaQueryWrapperX<RoleDO>().likeIfPresent(RoleDO::getName, reqDTO.getName()).likeIfPresent(RoleDO::getCode, reqDTO.getCode()).eqIfPresent(RoleDO::getStatus, reqDTO.getStatus()).betweenIfPresent(RoleDO::getCreateTime, reqDTO.getCreateTime()));
    }
}
