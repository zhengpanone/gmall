package com.zp.gmall.module.system.convert.config;

import com.zp.gmall.module.system.controller.admin.config.dto.ConfigDTO;
import com.zp.gmall.module.system.controller.admin.config.vo.ConfigVO;
import com.zp.gmall.module.system.entity.config.ConfigDO;
import org.mapstruct.Mapper;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.convert.config
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-06
 */
@Mapper
public interface ConfigConvertMapper {

    ConfigVO convert(ConfigDO configDO);


    ConfigDO convert(ConfigDTO dto);
}
