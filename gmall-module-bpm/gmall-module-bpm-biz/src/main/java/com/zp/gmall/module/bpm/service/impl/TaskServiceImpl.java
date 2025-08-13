package com.zp.gmall.module.bpm.service.impl;

import com.zp.gmall.module.bpm.factory.FlowableServiceFactory;
import com.zp.gmall.module.bpm.service.ITaskService;
import org.flowable.engine.task.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Author : zhengpanone
 * Date : 2025/5/16 17:07
 * Version : v1.0.0
 * Description:
 */
@Service
public class TaskServiceImpl extends FlowableServiceFactory implements ITaskService {

    @Override
    public void withdraw(String processInstanceId, String currentActivityId, String newActivityId) {
        runtimeService.createChangeActivityStateBuilder()
                .processInstanceId(processInstanceId)
                .moveActivityIdTo(currentActivityId, newActivityId)
                .changeState();
    }

    @Override
    public void addComment(String taskId, String procInsId, String message) {
        
    }

    @Override
    public List<Comment> getTaskComments(String taskId) throws Exception {
        return List.of();
    }

    @Override
    public void setAssignee(String taskId, String userId) {

    }

    @Override
    public Map<String, Object> complete(String taskId, Map<String, Object> variables, boolean localScope) {
        return Map.of();
    }

    @Override
    public void claim(String taskId, String userId) {

    }

    @Override
    public void unclaim(String taskId) {

    }

    @Override
    public void delegate(String taskId, String userId) {

    }

    @Override
    public void resolveTask(String taskId) {

    }
}
