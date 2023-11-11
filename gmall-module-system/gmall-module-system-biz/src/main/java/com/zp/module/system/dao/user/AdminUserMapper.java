package com.zp.module.system.dao.user;

import com.zp.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.module.system.dal.dataobject.user.AdminUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author : zhengpanone
 * Date : 2023/12/20 17:49
 * Version : v1.0.0
 
 */
@Mapper
public interface AdminUserMapper extends BaseMapperX<AdminUserDO> {

    default AdminUserDO selectByUsername(String username) {
        return selectOne(AdminUserDO::getUsername, username);
    }

    default AdminUserDO selectByMobile(String mobile) {
        return selectOne(AdminUserDO::getMobile, mobile);
    }
}
