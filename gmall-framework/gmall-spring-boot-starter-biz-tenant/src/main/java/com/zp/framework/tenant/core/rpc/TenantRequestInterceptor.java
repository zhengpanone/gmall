package com.zp.framework.tenant.core.rpc;

import cn.hutool.core.util.StrUtil;
import com.zp.framework.tenant.core.context.TenantContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import static com.zp.framework.web.core.util.WebFrameworkUtils.HEADER_TENANT_ID;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 17:11
 * Version : v1.0.0
 * Description:
 */
public class TenantRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String tenantId = TenantContextHolder.getTenantId();
        if (StrUtil.isBlank(tenantId)) {
            requestTemplate.header(HEADER_TENANT_ID, tenantId);
        }
    }
}
