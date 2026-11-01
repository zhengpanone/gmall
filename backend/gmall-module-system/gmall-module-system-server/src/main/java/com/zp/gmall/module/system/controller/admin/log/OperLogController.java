package com.zp.gmall.module.system.controller.admin.log;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.system.controller.admin.log.dto.OperLogPageDTO;
import com.zp.gmall.module.system.controller.admin.log.vo.OperLogVO;
import com.zp.gmall.module.system.service.log.IOperLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.controller.admin.operlog
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Tag(name = "操作日志管理")
@RestController
@RequestMapping("/log/operLog")
@RequiredArgsConstructor
@Validated
public class OperLogController {

    @Resource
    private final IOperLogService operLogService;


    @GetMapping("/page")
    @Operation(summary = "获取操作日志分页")
    public PageResult<OperLogVO> getOperLogPage(OperLogPageDTO dto) {
        return operLogService.getOperLogPage(dto);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除操作日志")
    public Result<?> deleteOperLog(@RequestBody @Valid Ids ids) {
        operLogService.deleteOperLog(ids);
        return Result.ok();
    }

    @DeleteMapping("/clean")
    @Operation(summary = "清空操作日志")
    public Result<?> cleanOperLog() {
        operLogService.cleanOperLog();
        return Result.ok();
    }
}
