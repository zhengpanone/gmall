package com.zp.gmall.module.system.mapper.permission;

import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.module.system.entity.permission.MenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : zhengpanone
 * Date : 2026/4/25 21:44
 * Version : v1.0.0
 * Description:
 */
@Mapper
public interface MenuMapper extends BaseMapperX<MenuDO> {

    List<MenuDO> selectMenuByUserId(String userId);

    List<String> selectPermsByUserId(String userId);
}
