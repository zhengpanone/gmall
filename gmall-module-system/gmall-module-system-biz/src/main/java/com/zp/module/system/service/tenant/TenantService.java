package com.zp.module.system.service.tenant;

import com.zp.module.system.dal.dataobject.tenant.TenantDO;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 11:35
 * Version : v1.0.0
 * Description: 租户 Service 接口
 */
public interface TenantService {

    /**
     * 获得域名对应的租户
     *
     * @param website 域名
     * @return 租户
     */
    TenantDO getTenantByWebsite(String website);
}
