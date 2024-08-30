package com.zp.module.system.dao.dept;

import com.zp.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zp.module.system.controller.admin.dept.dto.DeptListDTO;
import com.zp.module.system.dal.dataobject.dept.DeptDO;
import org.apache.ibatis.annotations.Mapper;


import java.util.Collection;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 12:47
 * Version : v1.0.0
 * Description:
 */
@Mapper
public interface DeptMapper extends BaseMapperX<DeptDO> {
    default List<DeptDO> selectList(DeptListDTO reqVO) {
        return selectList(new LambdaQueryWrapperX<DeptDO>()
                .likeIfPresent(DeptDO::getName, reqVO.getName())
                .eqIfPresent(DeptDO::getStatus, reqVO.getStatus()));
    }

    default DeptDO selectByParentIdAndName(String parentId, String name) {
        return selectOne(DeptDO::getParentId, parentId, DeptDO::getName, name);
    }

    default Long selectCountByParentId(String parentId) {
        return selectCount(DeptDO::getParentId, parentId);
    }

    default List<DeptDO> selectListByParentId(Collection<String> parentIds) {
        return selectList(DeptDO::getParentId, parentIds);
    }
}
