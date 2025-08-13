package com.zp.gmall.module.bpm.service.impl;

import com.zp.gmall.module.bpm.factory.FlowableServiceFactory;
import com.zp.gmall.module.bpm.service.IInstanceService;

import java.util.Map;

/**
 * Author : zhengpanone
 * Date : 2025/5/16 17:20
 * Version : v1.0.0
 * Description:
 */
public class InstanceServiceImpl extends FlowableServiceFactory implements IInstanceService {
    @Override
    public void addMultiInstanceExecution(String activityDefId, String processInstanceId, Map<String, Object> variables) {

    }

    @Override
    public void deleteMultiInstanceExecution(String processInstanceId, boolean flag) {

    }

    @Override
    public void suspendProcessInstanceById(String processInstanceId) {

    }

    @Override
    public void activateProcessInstanceById(String processInstanceId) {

    }
}
