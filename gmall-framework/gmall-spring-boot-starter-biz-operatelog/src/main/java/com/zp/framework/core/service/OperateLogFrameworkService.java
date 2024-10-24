package com.zp.framework.core.service;

/**
 * Author : zhengpanone
 * Date : 2023/12/17 12:57
 * Version : v1.0.0
 * Description: 操作日志 Framework Service 接口
 */
public interface OperateLogFrameworkService {
    /**
     * 记录操作日志
     *
     * @param operateLog 操作请求日志
     */
    void createOperateLog(OperateLog operateLog);
}
