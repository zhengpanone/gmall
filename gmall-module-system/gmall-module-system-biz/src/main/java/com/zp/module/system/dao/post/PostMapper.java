package com.zp.module.system.dao.post;

import com.zp.framework.common.pojo.PageResult;
import com.zp.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zp.module.system.controller.admin.post.PostPageDTO;
import com.zp.module.system.dal.dataobject.post.PostDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 19:59
 * Version : v1.0.0
 * Description:
 */
@Mapper
public interface PostMapper extends BaseMapperX<PostDO> {
    default List<PostDO> selectList(Collection<Long> ids, Collection<Integer> statuses) {
        return selectList(new LambdaQueryWrapperX<PostDO>()
                .inIfPresent(PostDO::getId, ids)
                .inIfPresent(PostDO::getStatus, statuses));
    }

    default PageResult<PostDO> selectPage(PostPageDTO dto) {
        return selectPage(dto, new LambdaQueryWrapperX<PostDO>()
                .likeIfPresent(PostDO::getCode, dto.getCode())
                .likeIfPresent(PostDO::getName, dto.getName())
                .eqIfPresent(PostDO::getStatus, dto.getStatus())
                .orderByDesc(PostDO::getId));
    }

    default PostDO selectByName(String name) {
        return selectOne(PostDO::getName, name);
    }

    default PostDO selectByCode(String code) {
        return selectOne(PostDO::getCode, code);
    }
}
