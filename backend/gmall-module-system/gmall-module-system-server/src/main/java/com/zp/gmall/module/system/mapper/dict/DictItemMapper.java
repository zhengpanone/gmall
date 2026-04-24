package com.zp.gmall.module.system.mapper.dict;


import com.zp.gmall.framework.common.pojo.PageResult;
import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.framework.mybatis.core.util.LambdaQueryWrapperX;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictPageReqVO;
import com.zp.gmall.module.system.entity.dict.DictItemDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

/**
 * @author : zhengpanone
 * Date : 2026/4/15 20:53
 * Version : v1.0.0
 * Description:
 */
@Mapper
public interface DictItemMapper extends BaseMapperX<DictItemDO> {

    default PageResult<DictItemDO> selectPage(DictPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DictItemDO>()
                .likeIfPresent(DictItemDO::getItemValue, reqVO.getName())
                .eqIfPresent(DictItemDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DictItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DictItemDO::getId));
    }

    default DictItemDO selectByName(String name) {
        return selectOne(DictItemDO::getItemValue, name);
    }

    @Update("UPDATE sys_dict_item SET deleted = 1, deleted_time = #{deletedTime} WHERE id = #{id}")
    void updateToDelete(@Param("id") Long id, @Param("deletedTime") LocalDateTime deletedTime);

}

