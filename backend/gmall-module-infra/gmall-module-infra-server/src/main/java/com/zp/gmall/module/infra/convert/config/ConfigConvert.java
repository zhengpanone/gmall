package com.zp.gmall.module.infra.convert.config;

import com.zp.gmall.module.infra.controller.admin.config.dto.ConfigDTO;
import com.zp.gmall.module.infra.controller.admin.config.vo.ConfigVO;
import com.zp.gmall.module.infra.entity.config.ConfigDO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfigConvert {

    ConfigVO convert(ConfigDO configDO);

    ConfigDO convert(ConfigDTO configDTO);
}
