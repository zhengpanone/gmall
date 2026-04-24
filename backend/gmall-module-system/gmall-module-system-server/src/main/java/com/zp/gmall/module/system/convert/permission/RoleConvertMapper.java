package com.zp.gmall.module.system.convert.permission;

import com.zp.gmall.module.system.controller.admin.permission.dto.RoleSaveDTO;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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


}
