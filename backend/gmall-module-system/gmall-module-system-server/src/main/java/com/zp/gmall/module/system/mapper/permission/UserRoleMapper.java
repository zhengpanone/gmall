package com.zp.gmall.module.system.mapper.permission;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.module.system.entity.permission.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : zhengpanone
 * Date : 2026/4/25 21:46
 * Version : v1.0.0
 * Description:
 */
@Mapper
public interface UserRoleMapper extends BaseMapperX<UserRoleDO> {

    default List<UserRoleDO> getListByUserId(String userId) {
        return this.selectList(Wrappers.<UserRoleDO>lambdaQuery().eq(UserRoleDO::getUserId, userId));
    }
}
