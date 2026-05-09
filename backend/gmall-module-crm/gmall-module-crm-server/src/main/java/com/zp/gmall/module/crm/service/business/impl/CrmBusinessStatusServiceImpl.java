package com.zp.gmall.module.crm.service.business.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.crm.entity.bussiness.CrmBusinessStatusDO;
import com.zp.gmall.module.crm.mapper.business.CrmBusinessStatusMapper;
import com.zp.gmall.module.crm.service.business.ICrmBusinessStatusService;
import org.springframework.stereotype.Service;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:31
 * Version : v1.0.0
 * Description: CRM 商机状态
 */
@Service
public class CrmBusinessStatusServiceImpl extends ServiceImpl<CrmBusinessStatusMapper, CrmBusinessStatusDO> implements ICrmBusinessStatusService {
}
