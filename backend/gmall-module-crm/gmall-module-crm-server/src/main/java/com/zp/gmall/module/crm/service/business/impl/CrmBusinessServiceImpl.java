package com.zp.gmall.module.crm.service.business.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.crm.entity.bussiness.CrmBusinessDO;
import com.zp.gmall.module.crm.mapper.business.CrmBusinessMapper;
import com.zp.gmall.module.crm.service.business.ICrmBusinessService;
import org.springframework.stereotype.Service;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:17
 * Version : v1.0.0
 * Description:
 */
@Service
public class CrmBusinessServiceImpl extends ServiceImpl<CrmBusinessMapper, CrmBusinessDO> implements ICrmBusinessService {
}
