package com.zp.gmall.module.system.convert.user;

import com.zp.gmall.module.system.controller.admin.user.dto.UserSaveDTO;
import com.zp.gmall.module.system.controller.admin.user.dto.UserUpdateDTO;
import com.zp.gmall.module.system.controller.admin.user.vo.AdminUserVO;
import com.zp.gmall.module.system.entity.user.UserDO;
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
public interface UserConvertMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    AdminUserVO convert(UserDO userDO);

    default List<AdminUserVO> convert(List<UserDO> userDOList) {
        return userDOList.stream().map(this::convert).collect(Collectors.toList());
    }


    @Mapping(target = "password", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "loginIp", ignore = true)
    @Mapping(target = "loginDate", ignore = true)
    UserDO convert(UserSaveDTO userSaveDTO);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "loginIp", ignore = true)
    @Mapping(target = "loginDate", ignore = true)
    UserDO convert(UserUpdateDTO userUpdateDTO);
}
