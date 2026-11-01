package com.zp.gmall.module.system.service.config;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.config.dto.ConfigDTO;
import com.zp.gmall.module.system.controller.admin.config.dto.ConfigPageDTO;
import com.zp.gmall.module.system.controller.admin.config.vo.ConfigVO;
import com.zp.gmall.module.system.entity.config.ConfigDO;

import java.util.List;
import java.util.Map;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.service.config
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-06
 */
public interface IConfigService extends IService<ConfigDO> {

    PageResult<ConfigVO> getConfigPage(ConfigPageDTO configPageDTO);

    ConfigDO createConfig(ConfigDTO configDTO);

    ConfigDO updateConfig(ConfigDTO configDTO);

    void deleteConfig(Ids ids);

    ConfigDO getById(String id);

    ConfigDO getByKey(String configKey);

    Map<String,ConfigVO> getByKeys(List<String> keys);
}
