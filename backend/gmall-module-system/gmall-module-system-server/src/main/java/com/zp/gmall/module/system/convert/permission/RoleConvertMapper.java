package com.zp.gmall.module.system.convert.permission;

import com.zp.gmall.module.system.controller.admin.permission.dto.RoleSaveDTO;
import com.zp.gmall.module.system.controller.admin.permission.dto.RoleUpdateDTO;
import com.zp.gmall.module.system.controller.admin.permission.vo.RoleVO;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author : zhengpanone
 * Date : 2026/4/25 00:14
 * Version : v1.0.0
 * Description:
 */
@Mapper(componentModel = "spring")
public interface RoleConvertMapper {

    /**
     * 转换角色保存DTO为角色DO
     *
     * @param roleSaveDTO 角色保存DTO
     * @return 角色DO
     */
    @Mapping(source = "roleName", target = "name")
    @Mapping(source = "roleCode", target = "code")
    @Mapping(source = "roleType", target = "type")
    RoleDO convert(RoleSaveDTO roleSaveDTO);

    /**
     * 转换角色更新DTO为角色DO
     *
     * @param roleUpdateDTO 角色更新DTO
     * @return 角色DO
     */
    @Mapping(source = "roleName", target = "name")
    @Mapping(source = "roleCode", target = "code")
    @Mapping(source = "roleType", target = "type")
    RoleDO convert(RoleUpdateDTO roleUpdateDTO);

    /**
     * 更新角色DO
     *
     * @param roleUpdateDTO 角色更新DTO
     * @param roleDO        角色DO
     */
    @Mapping(source = "roleName", target = "name")
    @Mapping(source = "roleCode", target = "code")
    @Mapping(source = "roleType", target = "type")
    void update(RoleUpdateDTO roleUpdateDTO, @MappingTarget RoleDO roleDO);

    /**
     * 转换角色DO为角色VO
     *
     * @param roleDO 角色DO
     * @return 角色VO
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "roleName")
    @Mapping(source = "code", target = "roleCode")
    @Mapping(source = "type", target = "roleType")
    RoleVO convert(RoleDO roleDO);
}
