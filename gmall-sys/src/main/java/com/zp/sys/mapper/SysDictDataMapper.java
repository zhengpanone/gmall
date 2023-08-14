/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.zp.sys.mapper;

import com.mybatisflex.core.BaseMapper;
import com.zp.sys.entity.DictData;
import com.zp.sys.entity.SysDictDataEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典数据
 *
 * @author zhengpanone@hotmail.com
 */
@Mapper
public interface SysDictDataMapper extends BaseMapper<SysDictDataEntity> {

    /**
     * 字典数据列表
     */
    List<DictData> getDictDataList();
}
