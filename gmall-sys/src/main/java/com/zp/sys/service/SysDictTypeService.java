/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.zp.sys.service;

import com.mybatisflex.core.service.IService;
import com.zp.sys.model.dto.SysDictTypeDTO;
import com.zp.sys.model.entity.DictType;
import com.zp.sys.model.entity.SysDictTypeEntity;

import java.util.List;

/**
 * 数据字典
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysDictTypeService extends IService<SysDictTypeEntity> {


    SysDictTypeDTO get(Long id);

    void save(SysDictTypeDTO dto);

    void update(SysDictTypeDTO dto);

    void delete(Long[] ids);

    /**
     * 获取所有字典
     */
    List<DictType> getAllList();

}