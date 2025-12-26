package com.zp.framework.tenant.core.service;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 14:41
 * Version : v1.0.0
 * Description:  Tenant 框架 Service 接口，定义获取租户信息
 */
public interface TenantFrameworkService {
    /**
     * 获得所有租户
     *
     * @return 租户编号数组
     */
    List<String> getTenantIds();

    /**
     * 校验租户是否合法
     *
     * @param id 租户编号
     */
    void validTenant(String id);
}
