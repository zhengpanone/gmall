package com.zp.module.system.service.tenant.impl;

import com.zp.framework.tenant.config.TenantProperties;
import com.zp.module.system.dal.dataobject.tenant.TenantDO;
import com.zp.module.system.dao.tenant.TenantMapper;
import com.zp.module.system.service.tenant.TenantService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 11:50
 * Version : v1.0.0
 * Description: 租户 Service 实现类
 */
@Service
@Slf4j
public class TenantServiceImpl implements TenantService {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired(required = false) // 由于 yudao.tenant.enable 配置项，可以关闭多租户的功能，所以这里只能不强制注入
    private TenantProperties tenantProperties;

    @Resource
    private TenantMapper tenantMapper;

    /**
     * 获得域名对应的租户
     *
     * @param website 域名
     * @return 租户
     */
    @Override
    public TenantDO getTenantByWebsite(String website) {
        return tenantMapper.selectByWebsite(website);
    }

    private boolean isTenantDisable() {
        return tenantProperties == null || Boolean.FALSE.equals(tenantProperties.getEnable());
    }
}
