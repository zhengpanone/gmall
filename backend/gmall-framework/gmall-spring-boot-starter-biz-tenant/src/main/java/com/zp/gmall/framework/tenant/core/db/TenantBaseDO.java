package com.zp.gmall.framework.tenant.core.db;

import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * Author : zhengpanone
 * Date : 2023/12/20 15:11
 * Version : v1.0.0
 * Description: 扩展多租户的BaseDO基类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class TenantBaseDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 多租户编号
     */
    private String tenantId;
}
