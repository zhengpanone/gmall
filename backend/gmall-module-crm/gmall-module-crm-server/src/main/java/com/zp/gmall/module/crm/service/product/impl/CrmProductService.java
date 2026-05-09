package com.zp.gmall.module.crm.service.product.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.crm.entity.product.CrmProductDO;
import com.zp.gmall.module.crm.mapper.product.CrmProductMapper;
import com.zp.gmall.module.crm.service.product.ICrmProductService;
import org.springframework.stereotype.Service;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:17
 * Version : v1.0.0
 * Description:
 */
@Service
public class CrmProductService extends ServiceImpl<CrmProductMapper, CrmProductDO> implements ICrmProductService {
}
