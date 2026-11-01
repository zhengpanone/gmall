package com.zp.gmall.module.crm.mapper.contract;

import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.module.crm.entity.contract.CrmContractProductDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:32
 * Version : v1.0.0
 * Description: CRM 合同与产品关联
 */
@Mapper
public interface CrmContractProductMapper  extends BaseMapperX<CrmContractProductDO> {
}
