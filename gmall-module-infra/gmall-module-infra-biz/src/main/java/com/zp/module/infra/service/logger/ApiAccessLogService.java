package com.zp.module.infra.service.logger;

import com.zp.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;

/**
 * Author : zhengpanone
 * Date : 2024/1/9 17:22
 * Version : v1.0.0
 * Description: API访问日志Service接口
 */
public interface ApiAccessLogService {
    /**
     * 创建API访问日志
     * @param apiAccessLogCreateReqDTO
     */
    void createApiAccessLog(ApiAccessLogCreateReqDTO apiAccessLogCreateReqDTO);
    // TODO
}
