package com.zp.framework.apilog.core.service.impl;


import com.zp.framework.apilog.core.service.ApiErrorLogFrameworkService;
import com.zp.module.infra.api.logger.ApiErrorLogApi;
import com.zp.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

/**
 * Author : zhengpanone
 * Date : 2023/11/16 16:58
 * Version : v1.0.0
 * Description: API 错误日志 Framework Service 实现类
 * 基于 {@link ApiErrorLogApi} 远程服务，记录错误日志
 */
@RequiredArgsConstructor
public class ApiErrorLogFrameworkServiceImpl implements ApiErrorLogFrameworkService {
    private final ApiErrorLogApi apiErrorLogApi;

    @Override
    @Async
    public void createApiErrorLog(ApiErrorLogCreateReqDTO reqDTO) {
        apiErrorLogApi.createApiErrorLog(reqDTO);
    }
}
