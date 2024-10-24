package com.zp.module.infra.service.logger.impl;

import com.zp.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import com.zp.module.infra.service.logger.ApiErrorLogService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Author : zhengpanone
 * Date : 2024/1/9 18:32
 * Version : v1.0.0
 * Description: API错误日志实现类
 */
@Service
@Validated
public class ApiErrorLogServiceImpl implements ApiErrorLogService {

    /**
     * 创建API错误日志
     *
     * @param createReqDTO API错误日志
     */
    @Override
    public void createApiErrorLog(ApiErrorLogCreateReqDTO createReqDTO) {

    }
}
