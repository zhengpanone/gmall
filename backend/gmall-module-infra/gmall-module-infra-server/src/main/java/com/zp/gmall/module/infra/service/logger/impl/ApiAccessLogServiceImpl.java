package com.zp.gmall.module.infra.service.logger.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.biz.infra.logger.dto.ApiAccessLogCreateReqDTO;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.infra.controller.admin.logger.dto.ApiAccessLogPageDTO;
import com.zp.gmall.module.infra.entity.logger.ApiAccessLogDO;
import com.zp.gmall.module.infra.mapper.logger.ApiAccessLogMapper;
import com.zp.gmall.module.infra.service.logger.IApiAccessLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApiAccessLogServiceImpl extends ServiceImpl<ApiAccessLogMapper, ApiAccessLogDO> implements IApiAccessLogService {

    @Override
    public void create(ApiAccessLogCreateReqDTO createReqDTO) {

    }

    @Override
    public ApiAccessLogDO getById(String id) {
        return null;
    }

    @Override
    public PageResult<ApiAccessLogDO> getPageList(ApiAccessLogPageDTO pageReqDTO) {
        return null;
    }

    @Override
    public Integer cleanAccessLog(Integer exceedDay, Integer deleteLimit) {
        return 0;
    }
}
