package com.zp.module.infra.convert.logger;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Author : zhengpanone
 * Date : 2024/1/9 17:28
 * Version : v1.0.0
 * Description: API访问日志
 */
@Mapper
public interface ApiAccessLogConvert {
    ApiAccessLogConvert INSTANCE = Mappers.getMapper(ApiAccessLogConvert.class);

    // ApiAccessLogRespVO convert(ApiAccessLogDO bean);
}
