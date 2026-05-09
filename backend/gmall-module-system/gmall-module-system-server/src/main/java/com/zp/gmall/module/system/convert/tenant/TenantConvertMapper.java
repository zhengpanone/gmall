package com.zp.gmall.module.system.convert.tenant;

import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantDTO;
import com.zp.gmall.module.system.controller.admin.tenant.vo.TenantVO;
import com.zp.gmall.module.system.entity.tenant.TenantDO;
import org.mapstruct.Mapper;

/**
 * 字典转换器
 *
 * @author zhengpan
 */
@Mapper(componentModel = "spring")
public interface TenantConvertMapper {

    TenantVO convert(TenantDO tenantDO);


    TenantDO convert(TenantDTO dto);




}
