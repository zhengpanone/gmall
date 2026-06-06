package com.zp.gmall.module.system.convert.config;

import com.zp.gmall.module.system.controller.admin.config.dto.ConfigDTO;
import com.zp.gmall.module.system.controller.admin.config.vo.ConfigVO;
import com.zp.gmall.module.system.controller.admin.permission.vo.MenuVO;
import com.zp.gmall.module.system.entity.config.ConfigDO;
import com.zp.gmall.module.system.entity.permission.MenuDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

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
public interface ConfigConvert {

    ConfigVO convert(ConfigDO configDO);


    ConfigDO convert(ConfigDTO dto);

    // List 转换（自动处理）
    List<ConfigVO> convertList(List<ConfigDO> dtoList);
}
