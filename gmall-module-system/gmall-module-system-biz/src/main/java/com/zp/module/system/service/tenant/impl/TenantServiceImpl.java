package com.zp.module.system.service.tenant.impl;

import com.zp.framework.common.pojo.PageResult;
import com.zp.framework.tenant.config.TenantProperties;
import com.zp.framework.tenant.core.context.TenantContextHolder;
import com.zp.module.system.controller.admin.tenant.dto.TenantPageDTO;
import com.zp.module.system.controller.admin.tenant.dto.TenantSaveDTO;
import com.zp.module.system.dal.dataobject.tenant.TenantDO;
import com.zp.module.system.dao.tenant.TenantMapper;
import com.zp.module.system.service.tenant.TenantService;
import com.zp.module.system.service.tenant.handler.TenantInfoHandler;
import com.zp.module.system.service.tenant.handler.TenantMenuHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    @Override
    public String createTenant(TenantSaveDTO createDTO) {
        return "";
    }

    @Override
    public void updateTenant(TenantSaveDTO updateDTO) {

    }

    @Override
    public void updateTenantRoleMenu(String tenantId, Set<String> menuIds) {

    }

    @Override
    public void deleteTenant(String id) {

    }

    @Override
    public TenantDO getTenant(String id) {
        return tenantMapper.selectById(id);
    }


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

    @Override
    public void handleTenantInfo(TenantInfoHandler handler) {
        // 如果禁用，则不执行逻辑
        if (isTenantDisable()) {
            return;
        }
        // 获得租户
        TenantDO tenant = getTenant(TenantContextHolder.getTenantId());
        // 执行处理器
        handler.handle(tenant);

    }

    @Override
    public PageResult<TenantDO> getTenantPage(TenantPageDTO pageDTO) {
        return null;
    }

    @Override
    public TenantDO getTenantByName(String name) {
        return null;
    }

    @Override
    public String getTenantCountByPackageId(String packageId) {
        return "";
    }

    @Override
    public List<TenantDO> getTenantListByPackageId(String packageId) {
        return List.of();
    }

    @Override
    public void handleTenantMenu(TenantMenuHandler handler) {

    }

    @Override
    public List<String> getTenantIdList() {
        return List.of();
    }

    @Override
    public void validTenant(String id) {

    }

    private boolean isTenantDisable() {
        return tenantProperties == null || Boolean.FALSE.equals(tenantProperties.getEnable());
    }
}
