/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.zp.sys.mapper;

import com.mybatisflex.core.BaseMapper;
import com.zp.sys.entity.DictType;
import com.zp.sys.entity.SysDictTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典类型
 *
 * @author zhengpanone@hotmail.com
 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictTypeEntity> {

    /**
     * 字典类型列表
     */
    List<DictType> getDictTypeList();

}
