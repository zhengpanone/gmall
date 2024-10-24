package com.zp.module.infra.service.logger;

import com.zp.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;

/**
 * Author : zhengpanone
 * Date : 2024/1/9 17:22
 * Version : v1.0.0
 * Description: API错误日志Service接口
 */
public interface ApiErrorLogService {
    /**
     * 创建API错误日志
     *
     * @param createReqDTO API错误日志
     */
    void createApiErrorLog(ApiErrorLogCreateReqDTO createReqDTO);
    // TODO
}
