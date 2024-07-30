package com.zp.module.infra.service.db;

import com.zp.module.infra.controller.admin.db.dto.DataSourceConfigSaveReqDTO;
import com.zp.module.infra.dal.dataobject.db.DataSourceConfigDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2024/7/8 20:52
 * Version : v1.0.0
 * Description: 数据源配置Service接口
 */
public interface DataSourceConfigService {
    /**
     * 创建数据源配置
     *
     * @param configSaveReqDTO 创建信息
     * @return 编号
     */
    String createDataSourceConfig(@Valid DataSourceConfigSaveReqDTO configSaveReqDTO);

    /**
     * 更新数据源配置
     *
     * @param updateReqVO 更新信息
     */
    void updateDataSourceConfig(@Valid DataSourceConfigSaveReqDTO updateReqVO);

    void deleteDataSourceConfig(Long id);

    /**
     * 获得数据源配置
     *
     * @param id 编号
     * @return 数据源配置
     */
    DataSourceConfigDO getDataSourceConfig(Long id);

    /**
     * 获得数据源配置列表
     *
     * @return 数据源配置列表
     */
    List<DataSourceConfigDO> getDataSourceConfigList();

}
