package com.zp.gmall.module.system.service.config.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.config.dto.ConfigDTO;
import com.zp.gmall.module.system.controller.admin.config.dto.ConfigPageDTO;
import com.zp.gmall.module.system.controller.admin.config.vo.ConfigVO;
import com.zp.gmall.module.system.convert.config.ConfigConvert;
import com.zp.gmall.module.system.entity.config.ConfigDO;
import com.zp.gmall.module.system.mapper.config.ConfigMapper;
import com.zp.gmall.module.system.service.config.IConfigService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Description: https://mp.weixin.qq.com/s/mnYJvMIfzyFv7AleqihN6g
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-06
 */
@Service
@CacheConfig(cacheNames = "config")
@RequiredArgsConstructor
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, ConfigDO> implements IConfigService {

    private final ConfigConvert convertMapper = Mappers.getMapper(ConfigConvert.class);

    private final CacheManager cacheManager;

    @Override
    public PageResult<ConfigVO> getConfigPage(ConfigPageDTO pageDTO) {
        Page<ConfigDO> page = new Page<>(pageDTO.getPageNo(), pageDTO.getPageSize());
        LambdaQueryWrapper<ConfigDO> queryWrapper = Wrappers.<ConfigDO>lambdaQuery()
                .like(StringUtils.isNotBlank(pageDTO.getConfigName()), ConfigDO::getConfigName, pageDTO.getConfigName())
                .like(StringUtils.isNotBlank(pageDTO.getConfigKey()), ConfigDO::getConfigKey, pageDTO.getConfigKey())
                .eq(StringUtils.isNotBlank(pageDTO.getConfigType()), ConfigDO::getConfigType, pageDTO.getConfigType())
                .eq(StringUtils.isNotBlank(pageDTO.getStatus()), ConfigDO::getStatus, pageDTO.getStatus())
                .orderByDesc(ConfigDO::getCreateTime);
        Page<ConfigDO> configDOPage = baseMapper.selectPage(page, queryWrapper);

        List<ConfigVO> voList = configDOPage.getRecords().stream().map(convertMapper::convert
        ).collect(Collectors.toList());

        return PageResult.ok(configDOPage.getTotal(), voList);
    }

    @CachePut(key = "#result.configKey")
    @Override
    public ConfigVO createConfig(ConfigDTO configDTO) {
        if (checkConfigKeyExists(configDTO.getConfigKey(), configDTO.getId())) {
            throw new RuntimeException("参数键已存在");
        }
        ConfigDO config = convertMapper.convert(configDTO);
        baseMapper.insert(config);
        return convertMapper.convert(config);
    }

    /**
     * 更新缓存
     */
    @CachePut(key = "#configDTO.configKey")
    @Override
    public ConfigVO updateConfig(ConfigDTO configDTO) {
        if (checkConfigKeyExists(configDTO.getConfigKey(), configDTO.getId())) {
            throw new RuntimeException("参数键已存在");
        }
        ConfigDO config = convertMapper.convert(configDTO);
        baseMapper.updateById(config);
        return convertMapper.convert(config);
    }

    /**
     * 更新后自动清除缓存
     */
    @Override
    public void deleteConfig(Ids ids) {
        baseMapper.deleteByIds(ids.getIds());
        Cache cache = cacheManager.getCache("config");
        if (cache != null) {
            ids.getIds().forEach(cache::evict);
        }
    }

    /**
     * 查询时自动缓存，key 为 "config::1"
     *
     * @param id 参数ID
     * @return VO
     */
    @Override
    @Cacheable(key = "#id", unless = "#result== null")
    public ConfigVO getById(String id) {
        ConfigDO config = baseMapper.selectById(id);
        return convertMapper.convert(config);
    }

    /**
     * 查询时自动缓存，key 为 "config::1"
     *
     * @param configKey 参数ID
     * @return VO
     */
    @Override
    @Cacheable(key = "#configKey", unless = "#result== null")
    public ConfigVO getByKey(String configKey) {
        ConfigDO config = baseMapper.selectFirstOne(ConfigDO::getConfigKey, configKey);
        return convertMapper.convert(config);
    }

    public Boolean checkConfigKeyExists(String configKey, String excludeId) {
        LambdaQueryWrapper<ConfigDO> queryWrapper = Wrappers.<ConfigDO>lambdaQuery()
                .eq(ConfigDO::getConfigKey, configKey)
                .eq(ConfigDO::getDeleted, 0)
                .ne(StringUtils.isNotBlank(excludeId), ConfigDO::getId, excludeId);

        return count(queryWrapper) > 0;
    }

}
