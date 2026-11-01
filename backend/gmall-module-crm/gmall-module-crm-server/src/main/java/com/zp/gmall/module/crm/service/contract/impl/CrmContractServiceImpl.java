package com.zp.gmall.module.crm.service.contract.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.crm.entity.contract.CrmContractDO;
import com.zp.gmall.module.crm.mapper.contract.CrmContractMapper;
import com.zp.gmall.module.crm.service.contract.ICrmContractService;
import org.springframework.stereotype.Service;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:32
 * Version : v1.0.0
 * Description: CRM 合同
 */
@Service
public class CrmContractServiceImpl extends ServiceImpl<CrmContractMapper, CrmContractDO> implements ICrmContractService {
}
