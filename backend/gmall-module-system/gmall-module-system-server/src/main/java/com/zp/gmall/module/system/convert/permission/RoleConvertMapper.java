package com.zp.gmall.module.system.convert.permission;

import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.module.system.controller.admin.permission.dto.RoleDTO;
import com.zp.gmall.module.system.controller.admin.permission.vo.RoleVO;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import com.zp.gmall.module.system.enums.permission.RoleTypeEnum;
import org.mapstruct.AfterMapping;
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
     * @param roleDTO 角色保存DTO
     * @return 角色DO
     */
    @Mapping(source = "roleName", target = "name")
    @Mapping(source = "roleCode", target = "code")
    @Mapping(source = "roleType", target = "type")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sort", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "remark", ignore = true)
    RoleDO convert(RoleDTO roleDTO);



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
    @Mapping(target = "statusName", ignore = true)
    @Mapping(target = "roleTypeName", ignore = true)
    RoleVO convert(RoleDO roleDO);


    @AfterMapping
    default void fillComputedFields(@MappingTarget RoleVO roleVO) {
        // 设置状态名称
        if (roleVO.getStatus() != null) {
            roleVO.setStatusName(CommonStatusEnum.getMessageByStatus(roleVO.getStatus()));
        }
        // 设置角色类型名称
        if (roleVO.getRoleType() != null) {
            RoleTypeEnum roleTypeEnum = RoleTypeEnum.getByType(roleVO.getRoleType());
            roleVO.setRoleTypeName(roleTypeEnum != null ? roleTypeEnum.getLabel() : "");
        }
    }
}
