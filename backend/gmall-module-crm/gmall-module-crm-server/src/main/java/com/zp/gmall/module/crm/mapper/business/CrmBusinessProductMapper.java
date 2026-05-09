package com.zp.gmall.module.crm.mapper.business;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.gmall.module.crm.entity.bussiness.CrmBusinessProductDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:31
 * Version : v1.0.0
 * Description: CRM 商机与产品关联
 */
@Mapper
public interface CrmBusinessProductMapper extends BaseMapper<CrmBusinessProductDO> {
}
