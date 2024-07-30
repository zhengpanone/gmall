package com.zp.module.infra.service.db.impl;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.zp.module.infra.controller.admin.db.dto.DataSourceConfigSaveReqDTO;
import com.zp.module.infra.dal.dataobject.db.DataSourceConfigDO;
import com.zp.module.infra.service.db.DataSourceConfigService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2024/7/8 21:18
 * Version : v1.0.0
 * Description: 数据源配置 Service 实现类
 */
@Service
@Validated
public class DataSourceConfigServiceImpl implements DataSourceConfigService {
    @Resource
    private DynamicDataSourceProperties dynamicDataSourceProperties;

    /**
     * 创建数据源配置
     *
     * @param configSaveReqDTO 创建信息
     * @return 编号
     */
    @Override
    public String createDataSourceConfig(DataSourceConfigSaveReqDTO configSaveReqDTO) {
        return "";
    }

    /**
     * 更新数据源配置
     *
     * @param updateReqVO 更新信息
     */
    @Override
    public void updateDataSourceConfig(DataSourceConfigSaveReqDTO updateReqVO) {

    }

    @Override
    public void deleteDataSourceConfig(Long id) {

    }

    /**
     * 获得数据源配置
     *
     * @param id 编号
     * @return 数据源配置
     */
    @Override
    public DataSourceConfigDO getDataSourceConfig(Long id) {
        return null;
    }

    /**
     * 获得数据源配置列表
     *
     * @return 数据源配置列表
     */
    @Override
    public List<DataSourceConfigDO> getDataSourceConfigList() {
        return List.of();
    }

    private DataSourceConfigDO buildMasterDataSourceConfig() {
        String primary = dynamicDataSourceProperties.getPrimary();
        DataSourceProperty dataSourceProperty = dynamicDataSourceProperties.getDatasource().get(primary);
        return new DataSourceConfigDO().setId(DataSourceConfigDO.ID_MASTER)
                .setName(primary)
                .setUrl(dataSourceProperty.getUrl())
                .setUsername(dataSourceProperty.getUsername())
                .setPassword(dataSourceProperty.getPassword());
    }
}
