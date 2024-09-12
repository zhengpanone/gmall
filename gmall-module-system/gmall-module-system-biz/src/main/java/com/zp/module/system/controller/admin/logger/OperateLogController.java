package com.zp.module.system.controller.admin.logger;

import com.zp.framework.apilog.core.annotation.ApiAccessLog;
import com.zp.framework.common.pojo.PageParam;
import com.zp.framework.common.pojo.PageResult;
import com.zp.framework.common.pojo.Result;
import com.zp.framework.common.util.object.BeanUtils;
import com.zp.framework.excel.core.util.ExcelUtils;
import com.zp.framework.translate.core.TranslateUtils;
import com.zp.module.system.api.logger.dto.OperateLogPageDTO;
import com.zp.module.system.controller.admin.logger.vo.OperateLogVO;
import com.zp.module.system.dal.dataobject.logger.OperateLogDO;
import com.zp.module.system.service.logger.OperateLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.zp.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static com.zp.framework.common.pojo.Result.ok;

/**
 * Author : zhengpanone
 * Date : 2024/8/30 15:24
 * Version : v1.0.0
 * Description: 操作日志
 */
@Tag(name = "管理后台 - 操作日志")
@RestController
@RequestMapping("/system/operate-log")
@Validated
public class OperateLogController {
    @Resource
    private OperateLogService operateLogService;

    @GetMapping("/page")
    @Operation(summary = "查看操作日志分页列表")
    //@PreAuthorize("@ss.hasPermission('system:operate-log:query')")
    public Result<PageResult<OperateLogVO>> pageOperateLog(@Valid OperateLogPageDTO operateLogPageDTO) {
        PageResult<OperateLogDO> operateLogPage = operateLogService.getOperateLogPage(operateLogPageDTO);
        return ok(BeanUtils.toBean(operateLogPage, OperateLogVO.class));
    }

    @Operation(summary = "导出操作日志")
    @GetMapping("/export")
    //@PreAuthorize("@ss.hasPermission('system:operate-log:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOperateLog(HttpServletResponse response, @Valid OperateLogPageDTO exportDTO) throws IOException {
        exportDTO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OperateLogDO> list = operateLogService.getOperateLogPage(exportDTO).getList();
        List<OperateLogVO> bean = BeanUtils.toBean(list, OperateLogVO.class);
        ExcelUtils.write(response, "操作日志.xls", "数据列表", OperateLogVO.class, TranslateUtils.translate(bean));
    }
}
