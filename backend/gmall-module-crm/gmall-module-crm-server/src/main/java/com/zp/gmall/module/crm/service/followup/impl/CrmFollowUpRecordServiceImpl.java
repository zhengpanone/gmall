package com.zp.gmall.module.crm.service.followup.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.crm.entity.followup.CrmFollowUpRecordDO;
import com.zp.gmall.module.crm.mapper.followup.CrmFollowUpRecordMapper;
import com.zp.gmall.module.crm.service.followup.ICrmFollowUpRecordService;
import org.springframework.stereotype.Service;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:34
 * Version : v1.0.0
 * Description: CRM 跟进记录
 */
@Service
public class CrmFollowUpRecordServiceImpl extends ServiceImpl<CrmFollowUpRecordMapper, CrmFollowUpRecordDO> implements ICrmFollowUpRecordService {
}
