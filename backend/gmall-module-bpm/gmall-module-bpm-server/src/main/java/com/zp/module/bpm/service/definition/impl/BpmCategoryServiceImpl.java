package com.zp.module.bpm.service.definition.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.module.bpm.entity.definition.BpmCategoryDO;
import com.zp.module.bpm.mapper.definition.BpmCategoryMapper;
import com.zp.module.bpm.service.definition.IBpmCategoryService;
import org.springframework.stereotype.Service;

@Service
public class BpmCategoryServiceImpl extends ServiceImpl<BpmCategoryMapper, BpmCategoryDO> implements IBpmCategoryService {
}
