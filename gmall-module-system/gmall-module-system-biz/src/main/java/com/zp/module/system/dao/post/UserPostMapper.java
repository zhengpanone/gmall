package com.zp.module.system.dao.post;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zp.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zp.module.system.dal.dataobject.post.UserPostDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 20:40
 * Version : v1.0.0
 * Description: 用户岗位Mapper
 */
@Mapper
public interface UserPostMapper extends BaseMapperX<UserPostDO> {
    default List<UserPostDO> selectListByUserId(String userId) {
        return selectList(UserPostDO::getUserId, userId);
    }

    default void deleteByUserIdAndPostId(String userId, Collection<String> postIds) {
        delete(new LambdaQueryWrapperX<UserPostDO>()
                .eq(UserPostDO::getUserId, userId)
                .in(UserPostDO::getPostId, postIds));
    }

    default List<UserPostDO> selectListByPostIds(Collection<String> postIds) {
        return selectList(UserPostDO::getPostId, postIds);
    }

    default void deleteByUserId(String userId) {
        delete(Wrappers.lambdaUpdate(UserPostDO.class).eq(UserPostDO::getUserId, userId));
    }
}
