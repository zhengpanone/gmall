package com.zp.module.infra.controller.admin.logger;

import com.zp.framework.common.pojo.PageResult;
import com.zp.framework.common.pojo.Result;
import com.zp.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogRespVO;
import com.zp.module.infra.service.logger.ApiAccessLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : zhengpanone
 * Date : 2024/1/10 13:47
 * Version : v1.0.0
 * Description: TODO
 */
@Tag(name = "管理后台 - API 访问日志")
@RestController
@RequestMapping("/infra/api-access-log")
@Validated
public class ApiAccessLogController {

    @Resource
    private ApiAccessLogService apiAccessLogService;

    @GetMapping("/page")
    @Operation(summary = "获得API 访问日志分页")
    @PreAuthorize("@ss.hasPermission('infra:api-access-log:query')")
    public Result<PageResult<ApiAccessLogRespVO>> getApiAccessLogPage(/*@Valid ApiAccessLogPageReqVO pageReqVO*/) {
        //PageResult<ApiAccessLogDO> pageResult = apiAccessLogService.getApiAccessLogPage(pageReqVO);
        //return Result.ok(BeanUtils.toBean(pageResult, ApiAccessLogRespVO.class));
        return Result.ok(null);
    }
}
