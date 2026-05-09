package com.zp.gmall.module.system.service.tenant;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantPackageDTO;
import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantPackagePageDTO;
import com.zp.gmall.module.system.controller.admin.tenant.vo.TenantPackageVO;
import com.zp.gmall.module.system.entity.tenant.TenantPackageDO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 *
 * Description: 租户套餐
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
public interface ITenantPackageService extends IService<TenantPackageDO> {

    void create(@Valid TenantPackageDTO tenantPackageDTO);

    TenantPackageVO queryById(@Valid @NotNull(message = "租户套餐ID不能为空") String id);

    PageResult<TenantPackageVO> getPageList(@Valid TenantPackagePageDTO tenantPackagePageDTO);

    void deleteByIds(@Valid Ids ids);

    void updateById(@Valid TenantPackageDTO tenantPackageDTO);

    List<TenantPackageVO> queryList(TenantPackageDTO dto);
}
