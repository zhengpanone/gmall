package com.zp.gmall.module.crm.service.business.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zp.gmall.module.crm.entity.bussiness.CrmBusinessProductDO;
import com.zp.gmall.module.crm.mapper.business.CrmBusinessProductMapper;
import com.zp.gmall.module.crm.service.business.ICrmBusinessProductService;
import org.springframework.stereotype.Service;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:31
 * Version : v1.0.0
 * Description: CRM 商机与产品关联
 */
@Service
public class CrmBusinessProductServiceImpl extends ServiceImpl<CrmBusinessProductMapper, CrmBusinessProductDO> implements ICrmBusinessProductService {
}
