package com.zp.gmall.module.system.mapper.permission;

import com.zp.gmall.framework.common.domain.dto.PageParam;
import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.module.system.controller.admin.permission.dto.RolePageDTO;
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

    /**
     * 根据角色编码查询角色信息
     *
     * @param roleCode 角色编码
     * @param tenantId 租户编号
     * @return 角色信息
     */
    RoleDO selectByCode(@Param("roleCode") String roleCode, @Param("tenantId") String tenantId);

    /**
     * 根据角色ID查询角色信息
     *
     * @param id 角色ID
     * @return 角色信息
     */
    RoleDO selectById(@Param("id") String id);
}
