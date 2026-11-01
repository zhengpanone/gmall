package com.zp.gmall.module.system.service.log.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.log.dto.OperLogPageDTO;
import com.zp.gmall.module.system.controller.admin.log.vo.OperLogVO;
import com.zp.gmall.module.system.entity.log.OperLogDO;
import com.zp.gmall.module.system.mapper.log.OperLogMapper;
import com.zp.gmall.module.system.service.log.IOperLogService;
import org.springframework.stereotype.Service;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Service
public class OperLogServiceImpl extends ServiceImpl<OperLogMapper, OperLogDO> implements IOperLogService {
    @Override
    public PageResult<OperLogVO> getOperLogPage(OperLogPageDTO dto) {
        return null;
    }

    @Override
    public void deleteOperLog(Ids ids) {

    }

    @Override
    public void cleanOperLog() {
        
    }
}
