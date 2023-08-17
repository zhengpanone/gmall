/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.zp.sys.service;

import com.mybatisflex.core.service.IService;
import com.zp.sys.model.dto.SysDictDataDTO;
import com.zp.sys.model.entity.SysDictDataEntity;

/**
 * 数据字典
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysDictDataService extends IService<SysDictDataEntity> {



    SysDictDataDTO get(Long id);

    void save(SysDictDataDTO dto);

    void update(SysDictDataDTO dto);

    boolean delete(Long[] ids);




}