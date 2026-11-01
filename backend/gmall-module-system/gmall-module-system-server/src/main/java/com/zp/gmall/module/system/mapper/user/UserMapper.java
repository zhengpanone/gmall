package com.zp.gmall.module.system.mapper.user;


import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.framework.mybatis.core.util.LambdaQueryWrapperX;
import com.zp.gmall.module.system.controller.admin.user.dto.UserPageDTO;
import com.zp.gmall.module.system.entity.user.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapperX<UserDO> {

    default UserDO selectByUsername(String username) {
        return selectOne(UserDO::getUsername, username);
    }

    default UserDO selectByEmail(String email) {
        return selectOne(UserDO::getEmail, email);
    }

    default UserDO selectByMobile(String mobile) {
        return selectOne(UserDO::getMobile, mobile);
    }

    default PageResult<UserDO> selectPage(UserPageDTO reqDTO, Collection<Long> deptIds, Collection<Long> userIds) {
        return selectPage(reqDTO, new LambdaQueryWrapperX<UserDO>()
                .likeIfPresent(UserDO::getUsername, reqDTO.getUsername())
                .likeIfPresent(UserDO::getMobile, reqDTO.getMobile())
                .eqIfPresent(UserDO::getStatus, reqDTO.getStatus())
                .betweenIfPresent(UserDO::getCreateTime, reqDTO.getCreateTime())
                .inIfPresent(UserDO::getId, userIds)
                .orderByDesc(UserDO::getId));
    }

    default List<UserDO> selectListByNickname(String nickname) {
        return selectList(new LambdaQueryWrapperX<UserDO>().like(UserDO::getNickname, nickname));
    }

    default List<UserDO> selectListByStatus(Integer status) {
        return selectList(UserDO::getStatus, status);
    }


}
