/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.zp.sys.service.impl;

import com.zp.common.service.impl.FlexBaseServiceImpl;
import com.zp.common.utils.ConvertUtils;
import com.zp.sys.dto.SysDictDataDTO;
import com.zp.sys.entity.SysDictDataEntity;
import com.zp.sys.mapper.SysDictDataMapper;
import com.zp.sys.service.SysDictDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * 字典类型
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class SysDictDataServiceImpl extends FlexBaseServiceImpl<SysDictDataMapper, SysDictDataEntity> implements SysDictDataService {





    @Override
    public SysDictDataDTO get(Long id) {
        SysDictDataEntity entity = mapper.selectOneById(id);

        return ConvertUtils.sourceToTarget(entity, SysDictDataDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictDataDTO dto) {
        SysDictDataEntity entity = ConvertUtils.sourceToTarget(dto, SysDictDataEntity.class);

        save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictDataDTO dto) {
        SysDictDataEntity entity = ConvertUtils.sourceToTarget(dto, SysDictDataEntity.class);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long[] ids) {
        //删除
        return removeByIds(Arrays.asList(ids));
    }

}