package com.zp.gmall.module.system.convert;

import com.zp.gmall.module.system.controller.admin.dto.UserSaveDTO;
import com.zp.gmall.module.system.controller.admin.dto.UserUpdateDTO;
import com.zp.gmall.module.system.controller.admin.user.vo.AdminUserVO;
import com.zp.gmall.module.system.entity.user.AdminUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Author : zhengpanone
 * Date : 2025/3/28 13:26
 * Version : v1.0.0
 * Description:
 */
@Mapper(componentModel = "spring")
public interface AdminUserConvertMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    AdminUserVO convert(AdminUserDO adminUserDO);

    default List<AdminUserVO> convert(List<AdminUserDO> adminUserDOList) {
        return adminUserDOList.stream().map(this::convert).collect(Collectors.toList());
    }


    @Mapping(target = "password", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "loginIp", ignore = true)
    @Mapping(target = "loginDate", ignore = true)
    AdminUserDO convert(UserSaveDTO userSaveDTO);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "loginIp", ignore = true)
    @Mapping(target = "loginDate", ignore = true)
    AdminUserDO convert(UserUpdateDTO userUpdateDTO);
}
