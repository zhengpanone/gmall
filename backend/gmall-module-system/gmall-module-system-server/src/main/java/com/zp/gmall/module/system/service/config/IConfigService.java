package com.zp.gmall.module.system.service.config;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.config.dto.ConfigPageDTO;
import com.zp.gmall.module.system.controller.admin.config.vo.ConfigVO;
import com.zp.gmall.module.system.entity.config.ConfigDO;

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
}
