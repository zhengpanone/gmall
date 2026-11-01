package com.zp.gmall.module.infra.mapper.config;

import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.module.infra.entity.config.ConfigDO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ConfigMapper extends BaseMapperX<ConfigDO> {
}
