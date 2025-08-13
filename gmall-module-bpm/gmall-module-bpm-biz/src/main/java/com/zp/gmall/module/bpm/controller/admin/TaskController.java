package com.zp.gmall.module.bpm.controller.admin;

import com.zp.gmall.framework.common.pojo.Result;
import com.zp.gmall.module.bpm.service.ITaskService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.flowable.engine.task.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Author : zhengpanone
 * Date : 2025/5/16 17:04
 * Version : v1.0.0
 * Description:
 */
@RestController
@RequestMapping("/bpm/task")
public class TaskController {
    @Resource
    private ITaskService taskService;

    @ResponseBody
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    @Operation(tags = "任务撤回", summary = "注意：当前与目标定义Key为设计模板时任务对应的ID,而非数据主键ID")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String"),
    //        @ApiImplicitParam(name = "currentTaskKey", value = "当前任务定义Key", required = true, dataType = "String"),
    //        @ApiImplicitParam(name = "targetTaskKey", value = "目标任务定义Key", required = true, dataType = "String")
    //})
    public Result<?> withdraw(String processInstanceId, String currentTaskKey, String targetTaskKey) {
        taskService.withdraw(processInstanceId, currentTaskKey, targetTaskKey);
        return Result.ok("任务撤回成功", null);
    }


    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    @ResponseBody
    //@ApiOperation(value = "执行任务", notes = "部门领导审批：{\"days\":\"6\",\"check\":\"1\",\"actorIds\":[\"wangwu\",\"lifei\"]}                                             \n" +
    //        "分管领导审批：{\"check\":\"1\",\"actorIds\":[\"hr_li\",\"hr_zhang\"]}", produces = "application/json")
    //@ApiImplicitParams({@ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String")})
    public Result<?> executeTask(String taskId, @RequestBody Map<String, Object> variables) throws Exception {
        Map<String, Object> map = taskService.complete(taskId, variables, false);

        return Result.ok(map);
    }


    @RequestMapping(value = "/claim", method = RequestMethod.POST)
    @ResponseBody
    //@ApiOperation(value = "任务签收", produces = "application/json")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
    //        @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String"),
    //})
    public Result<?> claim(String taskId, String userId) throws Exception {
        taskService.claim(taskId, userId);
        return Result.ok("任务签收成功");
    }


    @RequestMapping(value = "/unclaim", method = RequestMethod.POST)
    @ResponseBody
    //@ApiOperation(value = "任务反签收", produces = "application/json")
    //@ApiImplicitParams({@ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String")})
    public Result<?> unclaim(String taskId) throws Exception {
        taskService.unclaim(taskId);
        return Result.ok("任务反签收成功");
    }


    @RequestMapping(value = "/delegate", method = RequestMethod.POST)
    @ResponseBody
    //@ApiOperation(value = "任务委派", notes = "【act_ru_task】委派人：owner，被委派人：assignee，委派任务：delegateTask，任务回到委派人：resolveTask", produces = "application/json")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
    //        @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String"),
    //})
    public Result<?> delegate(String taskId, String userId) throws Exception {
        taskService.delegate(taskId, userId);
        return Result.ok("任务委派成功");
    }


    @RequestMapping(value = "/resolve", method = RequestMethod.POST)
    @ResponseBody
    //@ApiOperation(value = "任务归还：被委派人完成任务之后，将任务归还委派人", notes = "", produces = "application/json")
    //@ApiImplicitParams({@ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),})
    public Result<?> reslove(String taskId) throws Exception {
        taskService.resolveTask(taskId);
        return Result.ok();
    }


    @RequestMapping(value = "/assignee ", method = RequestMethod.POST)
    @ResponseBody
    //@ApiOperation(value = "任务转办", notes = "【act_ru_task】设置assignee为转办人", produces = "application/json")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
    //        @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String"),
    //})
    public Result<?> assignee(String taskId, String userId) throws Exception {
        taskService.setAssignee(taskId, userId);
        return Result.ok("任务转办成功", null);
    }


    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    @ResponseBody
    //@ApiOperation(value = "添加批注信息", notes = "批注信息：act_hi_comment", produces = "application/json")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
    //        @ApiImplicitParam(name = "instanceId", value = "实例ID", required = true, dataType = "String"),
    //        @ApiImplicitParam(name = "message", value = "批注内容", required = true, dataType = "String")
    //})
    public Result<?> addComment(String taskId, String instanceId, String message) throws Exception {
        taskService.addComment(taskId, instanceId, message);
        return Result.ok("添加批注信息成功", null);
    }

}
