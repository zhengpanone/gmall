package com.zp.gmall.module.system.convert.permission;

import com.zp.gmall.module.system.controller.admin.permission.dto.MenuDTO;
import com.zp.gmall.module.system.controller.admin.permission.vo.MenuTreeVO;
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

    @Mapping(target = "ancestorIds", ignore = true)
    @Mapping(target = "title", ignore = true)
    @Mapping(target = "cached", ignore = true)
    MenuDO convert(MenuDTO dto);


    @Mapping(source = "name", target = "name")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "type", target = "type")
    @Mapping(target = "children", ignore = true)
    MenuVO convert(MenuDO menuDO);

    // List 转换（自动处理）
    @Mapping(target = "children", ignore = true)
    List<MenuVO> convertList(List<MenuDO> menuDOList);

    // MenuTreeVO 转换
    @Mapping(target = "children", ignore = true)
    MenuTreeVO convertMenuTree(MenuDO menuDO);

    @Mapping(target = "children", ignore = true)
    List<MenuTreeVO> convertMenuTreeList(List<MenuDO> menuDOList);

}
