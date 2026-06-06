package com.zp.gmall.module.system.convert.auth;

import com.zp.gmall.module.system.controller.admin.auth.vo.AuthLoginVO;
import com.zp.gmall.module.system.controller.admin.auth.vo.AuthPermissionInfoVO;
import com.zp.gmall.module.system.controller.admin.user.vo.AdminUserVO;
import com.zp.gmall.module.system.convert.user.UserConvert;
import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;
import com.zp.gmall.module.system.entity.permission.MenuDO;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import com.zp.gmall.module.system.entity.user.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static com.zp.gmall.framework.common.util.collection.CollectionUtils.convertSet;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 19:15
 * Version : v1.0.0
 * Description:
 */
@Mapper(componentModel = "spring")
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    AuthLoginVO convert(OAuth2AccessTokenDO oAuth2AccessTokenDO);

    default AuthPermissionInfoVO convert(UserDO user, List<RoleDO> roleList, List<MenuDO> menuList){
        return AuthPermissionInfoVO.builder()
                .user(UserConvert.INSTANCE.convert(user))
                .roles(convertSet(roleList, RoleDO::getCode))
                .permissions(convertSet(menuList, MenuDO::getPermission))
//                .menus()
                .build();
    }

    default AuthPermissionInfoVO convert(AdminUserVO userVO, List<RoleDO> roleList, List<MenuDO> menuList){
        return AuthPermissionInfoVO.builder()
                .user(userVO)
                .roles(convertSet(roleList, RoleDO::getCode))
                .permissions(convertSet(menuList, MenuDO::getPermission))
//                .menus()
                .build();
    }

}
