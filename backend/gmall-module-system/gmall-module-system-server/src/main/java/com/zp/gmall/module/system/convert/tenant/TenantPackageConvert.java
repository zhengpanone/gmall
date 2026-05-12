package com.zp.gmall.module.system.convert.tenant;

import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantPackageDTO;
import com.zp.gmall.module.system.controller.admin.tenant.vo.TenantPackageVO;
import com.zp.gmall.module.system.entity.tenant.TenantPackageDO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 字典转换器
 *
 * @author zhengpan
 */
@Mapper(componentModel = "spring")
public interface TenantPackageConvert {

    TenantPackageVO convert(TenantPackageDO tenantPackageDO);


    TenantPackageDO convert(TenantPackageDTO dto);


    List<TenantPackageVO> convert(List<TenantPackageDO> doList);

}
