package com.zp.gmall.module.system.convert.tenant;

import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantPackageDTO;
import com.zp.gmall.module.system.controller.admin.tenant.vo.TenantPackageVO;
import com.zp.gmall.module.system.entity.tenant.TenantPackageDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典转换器
 *
 * @author zhengpan
 */
@Mapper(componentModel = "spring")
public interface TenantPackageConvert {

    TenantPackageConvert INSTANCE = Mappers.getMapper(TenantPackageConvert.class);

    @Mapping(source = "name", target = "packageName")
    @Mapping(source = "code", target = "packageCode")
    TenantPackageVO convert(TenantPackageDO tenantPackageDO);

    @Mapping(source = "packageName", target = "name")
    @Mapping(source = "packageCode", target = "code")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "menuIds", ignore = true)
    TenantPackageDO convert(TenantPackageDTO dto);


    List<TenantPackageVO> convert(List<TenantPackageDO> doList);

}
