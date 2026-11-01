package com.zp.gmall.module.system.service.config.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.constant.RedisKeyConstants;
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
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
@CacheConfig(cacheNames = RedisKeyConstants.CONFIG)
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

    @Caching(put = {
            @CachePut(key = "#result.id", unless = "#result == null || #result.id == null"),
            @CachePut(key = "#result.configKey", unless = "#result == null || #result.configKey == null")
    })
    @Override
    public ConfigDO createConfig(ConfigDTO configDTO) {
        if (checkConfigKeyExists(configDTO.getConfigKey(), configDTO.getId())) {
            throw new RuntimeException("参数键已存在");
        }
        ConfigDO config = convertMapper.convert(configDTO);
        baseMapper.insert(config);
        return config;
    }

    /**
     * 更新配置后，同步刷新 id 和 configKey 两种缓存 key。
     */
    @Caching(put = {
            @CachePut(key = "#result.id", unless = "#result == null || #result.id == null"),
            @CachePut(key = "#result.configKey", unless = "#result == null || #result.configKey == null")
    })
    @Override
    public ConfigDO updateConfig(ConfigDTO configDTO) {
        ConfigDO oldConfig = baseMapper.selectById(configDTO.getId());
        if (oldConfig == null) {
            throw new RuntimeException("参数不存在");
        }
        if (checkConfigKeyExists(configDTO.getConfigKey(), configDTO.getId())) {
            throw new RuntimeException("参数键已存在");
        }
        ConfigDO config = convertMapper.convert(configDTO);
        baseMapper.updateById(config);
        ConfigDO result = baseMapper.selectById(configDTO.getId());
        if (StringUtils.isNotBlank(oldConfig.getConfigKey())
                && !oldConfig.getConfigKey().equals(result.getConfigKey())) {
            evictConfigCaches(List.of(oldConfig.getConfigKey()));
        }
        return result;
    }

    /**
     * 删除配置后，同时清理 id 和 configKey 两种缓存 key。
     */
    @Override
    public void deleteConfig(Ids ids) {
        if (ids == null || CollUtil.isEmpty(ids.getIds())) {
            return;
        }
        Collection<? extends Serializable> configIds = ids.getIds();
        List<ConfigDO> configs = baseMapper.selectList(ConfigDO::getId, configIds);
        Collection<Object> cacheKeys = new LinkedHashSet<>(configIds);
        configs.stream()
                .map(ConfigDO::getConfigKey)
                .filter(StringUtils::isNotBlank)
                .forEach(cacheKeys::add);
        baseMapper.deleteByIds(configIds);
        evictConfigCaches(cacheKeys);
    }

    private void evictConfigCaches(Collection<?> keys) {
        if (CollUtil.isEmpty(keys)) {
            return;
        }
        Cache cache = cacheManager.getCache(RedisKeyConstants.CONFIG);
        if (cache != null) {
            keys.forEach(cache::evict);
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
    public ConfigDO getById(String id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询时自动缓存，key 为 "config::1"
     *
     * @param configKey 参数ID
     * @return VO
     */
    @Override
    @Cacheable(key = "#configKey", unless = "#result== null")
    public ConfigDO getByKey(String configKey) {
        return baseMapper.selectFirstOne(ConfigDO::getConfigKey, configKey);
    }

    @Override
    public Map<String, ConfigVO> getByKeys(List<String> keys) {
        if (CollUtil.isEmpty(keys)) {
            return Collections.emptyMap();
        }

        Cache cache = getConfigCache();
        Map<String, ConfigVO> result = new LinkedHashMap<>();
        List<String> missedKeys = new ArrayList<>();
        for (String key : keys) {
            if (StringUtils.isBlank(key) || result.containsKey(key)) {
                continue;
            }
            ConfigVO config = getConfigFromCache(cache, key);
            if (config == null) {
                missedKeys.add(key);
            } else {
                result.put(key, config);
            }
        }

        if (CollUtil.isEmpty(missedKeys)) {
            return result;
        }

        List<ConfigDO> list = baseMapper.selectList(Wrappers.<ConfigDO>lambdaQuery()
                .in(ConfigDO::getConfigKey, missedKeys));
        List<ConfigVO> configs = convertMapper.convertList(list);
        for (ConfigVO config : configs) {
            if (config == null || StringUtils.isBlank(config.getConfigKey())) {
                continue;
            }
            result.put(config.getConfigKey(), config);
            putConfigCaches(cache, config);
        }
        return result;
    }

    private Cache getConfigCache() {
        return cacheManager.getCache(RedisKeyConstants.CONFIG);
    }

    private ConfigVO getConfigFromCache(Cache cache, String key) {
        if (cache == null) {
            return null;
        }
        Cache.ValueWrapper valueWrapper = cache.get(key);
        if (valueWrapper == null || !(valueWrapper.get() instanceof ConfigVO config)) {
            return null;
        }
        return config;
    }

    private void putConfigCaches(Cache cache, ConfigVO config) {
        if (cache == null || config == null) {
            return;
        }
        if (StringUtils.isNotBlank(config.getId())) {
            cache.put(config.getId(), config);
        }
        if (StringUtils.isNotBlank(config.getConfigKey())) {
            cache.put(config.getConfigKey(), config);
        }
    }

    public Boolean checkConfigKeyExists(String configKey, String excludeId) {
        LambdaQueryWrapper<ConfigDO> queryWrapper = Wrappers.<ConfigDO>lambdaQuery()
                .eq(ConfigDO::getConfigKey, configKey)
                .eq(ConfigDO::getDeleted, 0)
                .ne(StringUtils.isNotBlank(excludeId), ConfigDO::getId, excludeId);

        return count(queryWrapper) > 0;
    }

}
