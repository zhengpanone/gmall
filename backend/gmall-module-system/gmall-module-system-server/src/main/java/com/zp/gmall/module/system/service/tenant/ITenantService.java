package com.zp.gmall.module.system.service.tenant;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantDTO;
import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantPageDTO;
import com.zp.gmall.module.system.controller.admin.tenant.vo.TenantVO;
import com.zp.gmall.module.system.entity.tenant.TenantDO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
public interface ITenantService extends IService<TenantDO> {
    void createTenant(@Valid TenantDTO tenantDTO);

    TenantVO getTenant(@Valid @NotNull(message = "租户ID不能为空") String id);

    PageResult<TenantVO> getTenantPage(@Valid TenantPageDTO tenantPageDTO);

    void deleteTenant(@Valid Ids ids);

    void updateTenant(@Valid TenantDTO tenantDTO);
}
