package com.zp.gmall.module.bpm.controller.admin;

import com.zp.gmall.framework.common.pojo.Result;
import com.zp.gmall.module.bpm.service.IInstanceService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Author : zhengpanone
 * Date : 2025/5/16 17:17
 * Version : v1.0.0
 * Description:
 */
@RestController
@RequestMapping("/bpm/instance")
public class InstanceController {
    @Resource
    private IInstanceService instanceService;

    @RequestMapping(value = "/suspendById", method = RequestMethod.GET)
    @ResponseBody
    //@ApiOperation(value = "挂起流程实例", produces = "application/json")
    //@ApiImplicitParams({@ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")})
    public Result<?> suspendById(String processInstanceId) throws Exception {
        instanceService.suspendProcessInstanceById(processInstanceId);
        return Result.ok("流程实例挂起成功", null);
    }


    @RequestMapping(value = "/activateById", method = RequestMethod.GET)
    @ResponseBody
    //@ApiOperation(value = "激活流程实例", produces = "application/json")
    //@ApiImplicitParams({@ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")})
    public Result<?> activateById(String processInstanceId) throws Exception {
        instanceService.activateProcessInstanceById(processInstanceId);
        return Result.ok("激活流程实例");
    }


    @RequestMapping(value = "/addMultiInstanceExecution", method = RequestMethod.POST)
    @ResponseBody
    //@ApiOperation(value = "多实例加签", notes = "数据变化：act_ru_task、act_ru_identitylink各生成一条记录", produces = "application/json")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "activityDefId", value = "流程环节定义ID", required = true, dataType = "String"),
    //        @ApiImplicitParam(name = "instanceId", value = "流程实例ID", required = true, dataType = "String")
    //})
    public Result<?> addMultiInstanceExecution(String activityDefId, String instanceId, @RequestBody Map<String, Object> variables) {
        instanceService.addMultiInstanceExecution(activityDefId, instanceId, variables);
        return Result.ok("加签成功");
    }

    @RequestMapping(value = "/deleteMultiInstanceExecution", method = RequestMethod.POST)
    @ResponseBody
    //@ApiOperation(value = "多实例减签", notes = "数据变化：act_ru_task减1、act_ru_identitylink不变", produces = "application/json")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "currentChildExecutionId", value = "子执行流ID", required = true, dataType = "String"),
    //        @ApiImplicitParam(name = "flag", value = "子执行流是否已执行", required = true, dataType = "boolean")
    //})
    public Result<?> deleteMultiInstanceExecution(String currentChildExecutionId, boolean flag) {
        instanceService.deleteMultiInstanceExecution(currentChildExecutionId, flag);
        return Result.ok("减签成功");
    }
}
