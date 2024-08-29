package com.zp.framework.security.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.zp.framework.common.core.KeyValue;
import com.zp.framework.security.core.service.SecurityFrameworkService;
import com.zp.module.system.api.permission.PermissionApi;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.zp.framework.common.util.cache.CacheUtils.buildCache;
import static com.zp.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.time.Duration;

/**
 * Author : zhengpanone
 * Date : 2024/8/6 16:38
 * Version : v1.0.0
 * Description: 默认的 {@link SecurityFrameworkService} 实现类
 */
@Service
@Slf4j
@AllArgsConstructor
public class SecurityFrameworkServiceImpl implements SecurityFrameworkService {

    private final PermissionApi permissionApi;

    /**
     * 针对 {@link #hasAnyRoles(String...)} 的缓存
     */
    private final LoadingCache<KeyValue<String, List<String>>, Boolean> hasAnyRolesCache = buildCache(Duration.ofMinutes(1L), // 过期时间 1 分钟
            new CacheLoader<KeyValue<String, List<String>>, Boolean>() {

                @Override
                public Boolean load(KeyValue<String, List<String>> key) {
                    return permissionApi.hasAnyRoles(key.getKey(), key.getValue().toArray(new String[0])).getCheckedData();
                }

            });

    /**
     * 针对 {@link #hasAnyPermissions(String...)} 的缓存
     */
    private final LoadingCache<KeyValue<String, List<String>>, Boolean> hasAnyPermissionsCache = buildCache(Duration.ofMinutes(1L), // 过期时间 1 分钟
            new CacheLoader<KeyValue<String, List<String>>, Boolean>() {

                @Override
                public Boolean load(KeyValue<String, List<String>> key) {
                    return permissionApi.hasAnyPermissions(key.getKey(), key.getValue().toArray(new String[0])).getCheckedData();
                }

            });


    /**
     * 判断是否有权限
     *
     * @param permission 权限
     * @return 是否
     */
    @Override
    public boolean hasPermission(String permission) {
        return hasAnyPermissions(permission);
    }

    /**
     * 判断是否有权限，任一一个即可
     *
     * @param permissions 权限
     * @return 是否
     */
    @Override
    @SneakyThrows
    public boolean hasAnyPermissions(String... permissions) {
        String userId = getLoginUserId();
        if (StrUtil.isBlank(userId)) {
            return false;
        }
        return hasAnyPermissionsCache.get(new KeyValue<>(userId, Arrays.asList(permissions)));
    }

    /**
     * 判断是否有角色
     * <p>
     * 注意，角色使用的是 SysRoleDO 的 code 标识
     *
     * @param role 角色
     * @return 是否
     */
    @Override
    public boolean hasRole(String role) {
        return false;
    }

    /**
     * 判断是否有角色，任一一个即可
     *
     * @param roles 角色数组
     * @return 是否
     */
    @Override
    public boolean hasAnyRoles(String... roles) {
        return false;
    }

    /**
     * 判断是否有授权
     *
     * @param scope 授权
     * @return 是否
     */
    @Override
    public boolean hasScope(String scope) {
        return false;
    }

    /**
     * 判断是否有授权范围，任一一个即可
     *
     * @param scope 授权范围数组
     * @return 是否
     */
    @Override
    public boolean hasAnyScopes(String... scope) {
        return false;
    }
}
