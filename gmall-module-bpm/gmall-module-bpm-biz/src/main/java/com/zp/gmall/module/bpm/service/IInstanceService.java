package com.zp.gmall.module.bpm.service;

import java.util.Map;

/**
 * Author : zhengpanone
 * Date : 2025/5/16 17:20
 * Version : v1.0.0
 * Description:
 */
public interface IInstanceService {
    /**
     * 多实例加签
     */
    void addMultiInstanceExecution(String activityDefId, String processInstanceId, Map<String, Object> variables);

    /**
     * 多实例减签
     */
    void deleteMultiInstanceExecution(String processInstanceId, boolean flag);

    /**
     * 挂起流程实例
     */
    void suspendProcessInstanceById(String processInstanceId);

    /**
     * 激活流程实例
     */
    void activateProcessInstanceById(String processInstanceId);

}
