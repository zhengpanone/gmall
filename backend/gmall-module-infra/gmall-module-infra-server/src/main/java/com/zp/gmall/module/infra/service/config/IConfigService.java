package com.zp.gmall.module.infra.service.config;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.infra.controller.admin.config.dto.ConfigDTO;
import com.zp.gmall.module.infra.controller.admin.config.dto.ConfigPageDTO;
import com.zp.gmall.module.infra.controller.admin.config.vo.ConfigVO;
import com.zp.gmall.module.infra.entity.config.ConfigDO;
import jakarta.validation.Valid;

public interface IConfigService extends IService<ConfigDO> {
    void createConfig(@Valid ConfigDTO configDTO);

    void updateConfig(@Valid ConfigDTO updateReqVO);

    void deleteConfig(Ids ids);

    PageResult<ConfigVO> getConfigPage(@Valid ConfigPageDTO dictPageDTO);

    ConfigVO getConfig(String id);
}
