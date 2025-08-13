package com.zp.gmall.module.bpm.service;

import org.flowable.engine.task.Comment;

import java.util.List;
import java.util.Map;

/**
 * Author : zhengpanone
 * Date : 2025/5/16 17:07
 * Version : v1.0.0
 * Description:
 */
public interface ITaskService {
    /**
     * 任务撤回
     *
     * @param processInstanceId 流程实例ID.
     * @param currentActivityId 当前活动任务ID.
     * @param newActivityId     撤回到达的任务ID.
     */
    void withdraw(String processInstanceId, String currentActivityId, String newActivityId);

    /**
     * 为流程任务 和/或 流程实例添加注释。
     *
     * @param taskId    流程任务ID.
     * @param procInsId 流程实例ID.
     * @param message   注释信息
     * @return
     * @throws Exception
     */
    void addComment(String taskId, String procInsId, String message);

    /**
     * 查询与任务相关的注释信息。
     *
     * @param taskId 流程任务ID.
     * @return
     * @throws Exception
     */
    List<Comment> getTaskComments(String taskId) throws Exception;

    /**
     * 任务移交：将任务的所有权转移给其他用户。
     *
     * @param taskId 任务的id，不能为null.
     * @param userId 接受所有权的人.
     */

    void setAssignee(String taskId, String userId);

    /**
     * 执行任务，并设置任务变量。
     *
     * @param taskId     任务的id，不能为null.
     * @param variables  任务变量.
     * @param localScope 存储范围。如果为true，则提供的变量将存储在任务本地（当任务结束后，再也取不到这个值），
     *                   而不是流程实例范围（默认是存放在流程实例中）。
     * @return
     * @throws
     */
    Map<String, Object> complete(String taskId, Map<String, Object> variables, boolean localScope);

    /**
     * 任务签收。
     *
     * @param taskId 任务的id，不能为null.
     * @param userId 签收人标识.
     * @return
     * @throws Exception
     */
    void claim(String taskId, String userId);

    /**
     * 任务反签收
     *
     * @param taskId 任务的id，不能为null.
     * @return
     * @throws Exception
     */
    void unclaim(String taskId);

    /**
     * 任务委派
     *
     * @param taskId 任务的id，不能为null.
     * @param userId 被委派人ID.
     */
    void delegate(String taskId, String userId);

    /**
     * 委派任务完成，归还委派人
     *
     * @param taskId 任务的id，不能为null.
     */
    void resolveTask(String taskId);
}
