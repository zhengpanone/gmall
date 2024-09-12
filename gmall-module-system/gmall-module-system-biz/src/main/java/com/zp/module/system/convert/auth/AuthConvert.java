package com.zp.module.system.convert.auth;

import cn.hutool.core.collection.CollUtil;
import com.zp.framework.common.util.object.BeanUtils;
import com.zp.module.system.controller.admin.auth.vo.AuthLoginVO;
import com.zp.module.system.controller.admin.auth.vo.AuthPermissionInfoVO;
import com.zp.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import com.zp.module.system.dal.dataobject.permission.MenuDO;
import com.zp.module.system.dal.dataobject.permission.RoleDO;
import com.zp.module.system.dal.dataobject.user.AdminUserDO;
import com.zp.module.system.enums.permission.MenuTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.zp.framework.common.util.collection.CollectionUtils.convertSet;

/**
 * Author : zhengpanone
 * Date : 2024/7/31 17:29
 * Version : v1.0.0
 * Description:
 */
@Mapper
public interface AuthConvert {
    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    AuthLoginVO convert(OAuth2AccessTokenDO bean);

    default AuthPermissionInfoVO convert(AdminUserDO user, List<RoleDO> roleList, List<MenuDO> menuList) {
        return AuthPermissionInfoVO.builder()
                .user(BeanUtils.toBean(user, AuthPermissionInfoVO.UserVO.class))
                .roles(convertSet(roleList, RoleDO::getCode))
                // 权限标识
                .permissions(convertSet(menuList, MenuDO::getPermission))
                // 菜单树
                .menus(buildMenuTree(menuList))
                .build();
    }

    /**
     * 将菜单列表构建成菜单树
     *
     * @param menuList 菜单列表
     * @return 菜单树
     */
    default List<AuthPermissionInfoVO.MenuVO> buildMenuTree(List<MenuDO> menuList) {
        if (CollUtil.isEmpty(menuList)) {
            return Collections.emptyList();
        }
        // 移除按钮
        menuList.removeIf(menu -> menu.getType().equals(MenuTypeEnum.BUTTON.getType()));
        // 排序，保证菜单的有序性
        menuList.sort(Comparator.comparing(MenuDO::getSort));


        return null;
    }
}
