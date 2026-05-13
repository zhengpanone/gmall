package com.zp.gmall.module.infra.service.logger.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.biz.infra.logger.dto.ApiErrorLogCreateReqDTO;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.infra.controller.admin.logger.dto.ApiAccessLogPageDTO;
import com.zp.gmall.module.infra.entity.logger.ApiErrorLogDO;
import com.zp.gmall.module.infra.mapper.logger.ApiErrorLogMapper;
import com.zp.gmall.module.infra.service.logger.IApiErrorLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-13
 */
@Slf4j
@Service
@Validated
public class ApiErrorLogServiceImpl extends ServiceImpl<ApiErrorLogMapper, ApiErrorLogDO> implements IApiErrorLogService {

    @Override
    public void create(ApiErrorLogCreateReqDTO createReqDTO) {

    }

    @Override
    public ApiErrorLogDO getById(String id) {
        return null;
    }

    @Override
    public PageResult<ApiErrorLogDO> getPageList(ApiAccessLogPageDTO pageReqDTO) {
        return null;
    }

    @Override
    public Integer cleanAccessLog(Integer exceedDay, Integer deleteLimit) {
        return 0;
    }
}
