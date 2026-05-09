package com.zp.gmall.module.crm.service.contract.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.crm.entity.contract.CrmContractProductDO;
import com.zp.gmall.module.crm.mapper.contract.CrmContractProductMapper;
import com.zp.gmall.module.crm.service.contract.ICrmContractProductService;
import org.springframework.stereotype.Service;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:32
 * Version : v1.0.0
 * Description: CRM 合同与产品关联
 */
@Service
public class CrmContractProductServiceImpl  extends ServiceImpl<CrmContractProductMapper, CrmContractProductDO> implements ICrmContractProductService {
}
