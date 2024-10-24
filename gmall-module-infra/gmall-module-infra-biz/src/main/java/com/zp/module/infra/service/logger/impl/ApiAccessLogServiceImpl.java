package com.zp.module.infra.service.logger.impl;

import com.zp.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;
import com.zp.module.infra.service.logger.ApiAccessLogService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Author : zhengpanone
 * Date : 2024/1/9 17:25
 * Version : v1.0.0
 * Description: API访问日志实现类
 */
@Service
@Validated
public class ApiAccessLogServiceImpl implements ApiAccessLogService {
    /**
     * 创建API访问日志
     *
     * @param createReqDTO
     */
    @Override
    public void createApiAccessLog(ApiAccessLogCreateReqDTO createReqDTO) {
        // ApiAccessLogCon

    }
}
