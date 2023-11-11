package com.zp.framework.apilog.core.service;

/**
 * Author : zhengpanone
 * Date : 2023/11/16 16:57
 * Version : v1.0.0
 * Description:  API 错误日志 Framework Service 接口
 */
public interface ApiErrorLogFrameworkService {
    /**
     * 创建API错误日志
     * @param apiErrorLog
     */
    void createApiErrorLog(ApiErrorLog apiErrorLog);
}
