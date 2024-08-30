package com.zp.module.system.service.tenant.handler;

import com.zp.module.system.dal.dataobject.tenant.TenantDO;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 20:10
 * Version : v1.0.0
 * Description:  租户信息处理
 *  目的：尽量减少租户逻辑耦合到系统中
 */
public interface TenantInfoHandler {
    /**
     * 基于传入的租户信息，进行相关逻辑的执行
     * 例如说，创建用户时，超过最大账户配额
     *
     * @param tenant 租户信息
     */
    void handle(TenantDO tenant);

}
