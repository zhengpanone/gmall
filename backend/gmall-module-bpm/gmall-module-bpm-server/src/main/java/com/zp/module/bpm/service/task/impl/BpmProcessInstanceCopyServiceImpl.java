package com.zp.module.bpm.service.task.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.module.bpm.entity.task.BpmProcessInstanceCopyDO;
import com.zp.module.bpm.mapper.task.BpmProcessInstanceCopyMapper;
import com.zp.module.bpm.service.task.IBpmProcessInstanceCopyService;
import org.springframework.stereotype.Service;

@Service
public class BpmProcessInstanceCopyServiceImpl extends ServiceImpl<BpmProcessInstanceCopyMapper, BpmProcessInstanceCopyDO> implements IBpmProcessInstanceCopyService {
}
