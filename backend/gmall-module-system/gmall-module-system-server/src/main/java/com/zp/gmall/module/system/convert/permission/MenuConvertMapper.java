package com.zp.gmall.module.system.convert.permission;

import com.zp.gmall.module.system.controller.admin.permission.dto.MenuDTO;
import com.zp.gmall.module.system.controller.admin.permission.vo.MenuVO;
import com.zp.gmall.module.system.entity.permission.MenuDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author : zhengpanone
 * Date : 2026/4/25 00:14
 * Version : v1.0.0
 * Description:
 */
@Mapper(componentModel = "spring")
public interface MenuConvertMapper {

    @Mapping(source = "menuName", target = "name")
    @Mapping(source = "menuType", target = "type")
    @Mapping(source = "menuCode", target = "code")
    MenuDO convert(MenuDTO dto);


    @Mapping(source = "name", target = "menuName")
    @Mapping(source = "code", target = "menuCode")
    MenuVO convert(MenuDO menuDO);

    // List 转换（自动处理）
    List<MenuVO> convertList(List<MenuDO> menuDOList);

}
