package com.zp.framework.tenant.core.service.impl;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.zp.framework.common.pojo.Result;
import com.zp.framework.tenant.core.service.TenantFrameworkService;
import com.zp.module.system.api.tenant.TenantApi;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.zp.framework.common.util.cache.CacheUtils.buildAsyncReloadingCache;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 16:30
 * Version : v1.0.0
 * Description: Tenant 框架 Service 实现类
 */
@RequiredArgsConstructor
public class TenantFrameworkServiceImpl implements TenantFrameworkService {
    private final TenantApi tenantApi;
    /**
     * 针对 {@link #getTenantIds()} 的缓存
     */
    private final LoadingCache<Object, List<String>> getTenantIdsCache = buildAsyncReloadingCache(
            // 过期时间1分钟
            Duration.ofMinutes(1L),
            new CacheLoader<>() {
                @Override
                public List<String> load(Object key) {
                    return tenantApi.getTenantIdList().getCheckedData();
                }
            });

    /**
     * 针对 {@link #validTenant(String)} 的缓存
     */
    private final LoadingCache<String, Result<Boolean>> validTenantCache = buildAsyncReloadingCache(
            Duration.ofMinutes(1L),
            new CacheLoader<>() {
                @Override
                public Result<Boolean> load(String id) {
                    return tenantApi.validTenant(id);
                }
            });

    /**
     * 获得所有租户
     *
     * @return 租户编号数组
     */
    @SneakyThrows
    @Override
    public List<String> getTenantIds() {
        return getTenantIdsCache.get(Boolean.TRUE);
    }

    /**
     * 校验租户是否合法
     *
     * @param id 租户编号
     */
    @SneakyThrows
    @Override
    public void validTenant(String id) {
        validTenantCache.get(id).checkError();
    }
}
