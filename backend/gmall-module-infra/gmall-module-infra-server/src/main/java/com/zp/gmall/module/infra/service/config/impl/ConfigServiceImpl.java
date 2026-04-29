package com.zp.gmall.module.infra.service.config.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.infra.controller.admin.config.dto.ConfigDTO;
import com.zp.gmall.module.infra.controller.admin.config.dto.ConfigPageDTO;
import com.zp.gmall.module.infra.controller.admin.config.vo.ConfigVO;
import com.zp.gmall.module.infra.convert.config.ConfigConvert;
import com.zp.gmall.module.infra.entity.config.ConfigDO;
import com.zp.gmall.module.infra.mapper.config.ConfigMapper;
import com.zp.gmall.module.infra.service.config.IConfigService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, ConfigDO> implements IConfigService {

    private final ConfigConvert convertMapper = Mappers.getMapper(ConfigConvert.class);

    @Override
    public void createConfig(ConfigDTO configDTO) {
        ConfigDO configDO = convertMapper.convert(configDTO);
        baseMapper.insert(configDO);
    }

    @Override
    public void updateConfig(ConfigDTO configDTO) {
        ConfigDO configDO = convertMapper.convert(configDTO);
        baseMapper.updateById(configDO);
    }

    @Override
    public void deleteConfig(Ids ids) {

    }

    @Override
    public PageResult<ConfigVO> getConfigPage(ConfigPageDTO dictPageDTO) {
        return null;
    }

    @Override
    public ConfigVO getConfig(String id) {
        return null;
    }
}
