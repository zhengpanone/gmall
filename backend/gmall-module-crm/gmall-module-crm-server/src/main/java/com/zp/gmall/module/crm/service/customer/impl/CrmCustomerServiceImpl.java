package com.zp.gmall.module.crm.service.customer.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.crm.entity.customer.CrmCustomerDO;
import com.zp.gmall.module.crm.mapper.customer.CrmCustomerMapper;
import com.zp.gmall.module.crm.service.customer.ICrmCustomerService;
import org.springframework.stereotype.Service;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:33
 * Version : v1.0.0
 * Description:
 */
@Service
public class CrmCustomerServiceImpl extends ServiceImpl<CrmCustomerMapper, CrmCustomerDO> implements ICrmCustomerService {
}
