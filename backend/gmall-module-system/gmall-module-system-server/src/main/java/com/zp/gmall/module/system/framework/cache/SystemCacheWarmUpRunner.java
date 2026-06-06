package com.zp.gmall.module.system.framework.cache;

import com.zp.gmall.module.system.constant.RedisKeyConstants;
import com.zp.gmall.module.system.controller.admin.config.vo.ConfigVO;
import com.zp.gmall.module.system.convert.config.ConfigConvert;
import com.zp.gmall.module.system.entity.config.ConfigDO;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import com.zp.gmall.module.system.mapper.config.ConfigMapper;
import com.zp.gmall.module.system.mapper.permission.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 启动时预热系统基础缓存。
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SystemCacheWarmUpRunner implements ApplicationRunner {

    private final RoleMapper roleMapper;

    private final ConfigMapper configMapper;

    private final CacheManager cacheManager;

    private final ConfigConvert configConvert = Mappers.getMapper(ConfigConvert.class);

    @Override
    public void run(ApplicationArguments args) {
        warmUpRoleCache();
        warmUpConfigCache();
    }

    private void warmUpRoleCache() {
        Cache cache = cacheManager.getCache(RedisKeyConstants.ROLE);
        if (cache == null) {
            log.warn("角色缓存预热失败，缓存 [{}] 不存在", RedisKeyConstants.ROLE);
            return;
        }
        List<RoleDO> roles = roleMapper.selectList();
        int cacheCount = 0;
        for (RoleDO role : roles) {
            if (role.getId() == null) {
                continue;
            }
            cache.put(role.getId(), role);
            cacheCount++;
        }
        log.info("角色缓存预热完成，数量：{}", cacheCount);
    }

    private void warmUpConfigCache() {
        Cache cache = cacheManager.getCache(RedisKeyConstants.CONFIG);
        if (cache == null) {
            log.warn("系统配置缓存预热失败，缓存 [{}] 不存在", RedisKeyConstants.CONFIG);
            return;
        }
        List<ConfigDO> configs = configMapper.selectList();
        int cacheCount = 0;
        for (ConfigDO config : configs) {
            ConfigVO configVO = configConvert.convert(config);
            if (StringUtils.isNotBlank(config.getId())) {
                cache.put(config.getId(), configVO);
                cacheCount++;
            }
            if (StringUtils.isNotBlank(config.getConfigKey())) {
                cache.put(config.getConfigKey(), configVO);
                cacheCount++;
            }
        }
        log.info("系统配置缓存预热完成，数量：{}", cacheCount);
    }
}
