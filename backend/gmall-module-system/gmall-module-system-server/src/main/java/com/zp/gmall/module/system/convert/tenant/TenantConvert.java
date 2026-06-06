package com.zp.gmall.module.system.convert.tenant;

import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantDTO;
import com.zp.gmall.module.system.controller.admin.tenant.vo.TenantVO;
import com.zp.gmall.module.system.convert.dict.DictDataConvert;
import com.zp.gmall.module.system.entity.tenant.TenantDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 字典转换器
 *
 * @author zhengpan
 */
@Mapper(componentModel = "spring")
public interface TenantConvert {

    TenantConvert INSTANCE = Mappers.getMapper(TenantConvert.class);

    @Mapping(source = "name", target = "tenantName")
    @Mapping(source = "code", target = "tenantCode")
    TenantVO convert(TenantDO tenantDO);

    @Mapping(source = "tenantName", target = "name")
    @Mapping(source = "tenantCode", target = "code")
    TenantDO convert(TenantDTO dto);


}
