package com.zp.framework.apilog.core.service.impl;


import com.zp.framework.apilog.core.service.ApiAccessLogFrameworkService;
import com.zp.module.infra.api.logger.ApiAccessLogApi;
import com.zp.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

/**
 * Author : zhengpanone
 * Date : 2023/11/14 22:33
 * Version : v1.0.0
 * Description: API访问日志Framework Service实现类
 * 基于{@link ApiAccessLogApi } 远程服务,记录访问日志
 */
@RequiredArgsConstructor
public class ApiAccessLogFrameworkServiceImpl implements ApiAccessLogFrameworkService {
    private final ApiAccessLogApi apiAccessLogApi;

    @Override
    @Async
    public void createApiAccessLog(ApiAccessLogCreateReqDTO reqDTO) {
        apiAccessLogApi.createApiAccessLog(reqDTO);
    }
}
