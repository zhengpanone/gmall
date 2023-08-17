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
import com.zp.sys.model.dto.SysDictTypeDTO;
import com.zp.sys.model.entity.DictData;
import com.zp.sys.model.entity.DictType;
import com.zp.sys.model.entity.SysDictTypeEntity;
import com.zp.sys.mapper.SysDictDataMapper;
import com.zp.sys.mapper.SysDictTypeMapper;
import com.zp.sys.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 字典类型
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class SysDictTypeServiceImpl extends FlexBaseServiceImpl<SysDictTypeMapper, SysDictTypeEntity> implements SysDictTypeService {
    @Autowired
    private SysDictDataMapper sysDictDataMapper;


    @Override
    public SysDictTypeDTO get(Long id) {
        SysDictTypeEntity entity = mapper.selectOneById(id);

        return ConvertUtils.sourceToTarget(entity, SysDictTypeDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictTypeDTO dto) {
        SysDictTypeEntity entity = ConvertUtils.sourceToTarget(dto, SysDictTypeEntity.class);

        save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictTypeDTO dto) {
        SysDictTypeEntity entity = ConvertUtils.sourceToTarget(dto, SysDictTypeEntity.class);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        //删除
        removeByIds(Arrays.asList(ids));
    }

    @Override
    public List<DictType> getAllList() {
        List<DictType> typeList = mapper.getDictTypeList();
        List<DictData> dataList = sysDictDataMapper.getDictDataList();
        for (DictType type : typeList) {
            for (DictData data : dataList) {
                if (type.getId().equals(data.getDictTypeId())) {
                    type.getDataList().add(data);
                }
            }
        }
        return typeList;
    }

}