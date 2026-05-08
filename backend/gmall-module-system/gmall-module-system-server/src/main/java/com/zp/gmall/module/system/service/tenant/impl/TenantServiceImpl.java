package com.zp.gmall.module.system.service.tenant.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantDTO;
import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantPageDTO;
import com.zp.gmall.module.system.controller.admin.tenant.vo.TenantVO;
import com.zp.gmall.module.system.entity.tenant.TenantDO;
import com.zp.gmall.module.system.mapper.tenant.TenantMapper;
import com.zp.gmall.module.system.service.tenant.ITenantService;
import org.springframework.stereotype.Service;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, TenantDO> implements ITenantService {
    @Override
    public void createTenant(TenantDTO tenantDTO) {

    }

    @Override
    public TenantVO getTenant(String id) {
        return null;
    }

    @Override
    public PageResult<TenantVO> getTenantPage(TenantPageDTO tenantPageDTO) {
        return null;
    }

    @Override
    public void deleteTenant(Ids ids) {

    }

    @Override
    public void updateTenant(TenantDTO tenantDTO) {

    }
}
