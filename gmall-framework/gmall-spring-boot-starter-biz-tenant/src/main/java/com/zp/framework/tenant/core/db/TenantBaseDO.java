package com.zp.framework.tenant.core.db;

import com.zp.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Author : zhengpanone
 * Date : 2023/12/20 15:11
 * Version : v1.0.0
 * Description: 扩展多租户的BaseDO基类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class TenantBaseDO extends BaseDO {
    /**
     * 多租户编号
     */
    private Long tenantId;
}
