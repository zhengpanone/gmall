package com.zp.gmall.module.system.mapper.permission;

import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : zhengpanone
 * Date : 2026/4/24 23:46
 * Version : v1.0.0
 * Description:
 */
@Mapper
public interface RoleMapper extends BaseMapperX<RoleDO> {
    /**
     * 根据角色名称查询角色信息
     *
     * @param roleName 角色名称
     * @param tenantId 租户编号
     * @return 角色信息
     */
    RoleDO selectByName(@Param("roleName") String roleName, @Param("tenantId") String tenantId);

    RoleDO selectByCode(@Param("roleCode") String roleCode,@Param("tenantId") String tenantId);
}
