package com.zp.module.system.service.tenant;

import com.zp.framework.tenant.core.context.TenantContextHolder;
import com.zp.module.system.dal.dataobject.tenant.TenantDO;
import com.zp.module.system.service.tenant.handler.TenantInfoHandler;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 11:35
 * Version : v1.0.0
 * Description: 租户 Service 接口
 */
public interface
TenantService {
    /**
     * 获得租户
     *
     * @param id 编号
     * @return 租户
     */
    TenantDO getTenant(String id);

    /**
     * 获得域名对应的租户
     *
     * @param website 域名
     * @return 租户
     */
    TenantDO getTenantByWebsite(String website);


    /**
     * 进行租户的信息处理逻辑
     * 其中，租户编号从 {@link TenantContextHolder} 上下文中获取
     *
     * @param handler 处理器
     */
    void handleTenantInfo(TenantInfoHandler handler);
}
