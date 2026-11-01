package com.zp.gmall.module.infra.mapper.logger;

import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.module.infra.entity.logger.ApiAccessLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-13
 */
@Mapper
public interface ApiAccessLogMapper extends BaseMapperX<ApiAccessLogDO> {
}
