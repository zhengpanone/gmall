package com.zp.module.bpm.facade;


import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

@Service
public class FlowableFacade {

    private final RepositoryService repositoryService;

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    private final HistoryService historyService;


    public FlowableFacade(
            RepositoryService repositoryService,
            RuntimeService runtimeService,
            TaskService taskService,
            HistoryService historyService) {

        this.repositoryService = repositoryService;
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.historyService = historyService;
    }


    /**
     * 部署流程
     */
    public String deploy(String resourceName) {
        return repositoryService
                .createDeployment()
                .addClasspathResource(resourceName)
                .deploy()
                .getId();
    }


    /**
     * 启动流程
     */
    public String startProcess(
            String processKey,
            String businessKey) {
        ProcessInstance instance =
                runtimeService
                        .startProcessInstanceByKey(
                                processKey,
                                businessKey
                        );

        return instance.getId();
    }


    /**
     * 查询流程实例
     */
    public ProcessInstance getProcessInstance(
            String processId) {

        return runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(processId)
                .singleResult();
    }


    /**
     * 完成任务
     */
    public void completeTask(
            String taskId) {
        taskService.complete(taskId);
    }


    /**
     * 删除流程
     */
    public void deleteProcess(
            String processId,
            String reason) {
        runtimeService
                .deleteProcessInstance(
                        processId,
                        reason
                );
    }
}