package com.zp.module.system.service.logger.impl;

import com.zp.framework.common.pojo.PageResult;
import com.zp.framework.common.util.object.BeanUtils;
import com.zp.module.system.api.logger.dto.OperateLogCreateDTO;
import com.zp.module.system.api.logger.dto.OperateLogPageDTO;
import com.zp.module.system.dal.dataobject.logger.OperateLogDO;
import com.zp.module.system.dao.logger.OperateLogMapper;
import com.zp.module.system.service.logger.OperateLogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Author : zhengpanone
 * Date : 2024/8/30 15:11
 * Version : v1.0.0
 * Description:
 */
@Service
@Validated
@Slf4j
public class OperateLogServiceImpl implements OperateLogService {
    @Resource
    private OperateLogMapper operateLogMapper;

    @Override
    public void createOperateLog(OperateLogCreateDTO createDTO) {
        OperateLogDO log = BeanUtils.toBean(createDTO, OperateLogDO.class);
        operateLogMapper.insert(log);
    }

    @Override
    public PageResult<OperateLogDO> getOperateLogPage(OperateLogPageDTO operateLogPageDTO) {
        return operateLogMapper.selectPage(operateLogPageDTO);
    }
}
