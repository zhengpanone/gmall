package com.zp.module.system.api.logger;

import com.zp.framework.common.pojo.PageResult;
import com.zp.framework.common.pojo.Result;
import com.zp.module.system.api.logger.dto.OperateLogCreateDTO;
import com.zp.module.system.api.logger.dto.OperateLogPageDTO;
import com.zp.module.system.api.logger.vo.OperateLogVO;
import com.zp.module.system.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Author : zhengpanone
 * Date : 2024/8/5 13:29
 * Version : v1.0.0
 * Description: TODO
 */
@FeignClient(name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - 操作日志")
public interface OperateLogApi {
    String PREFIX = ApiConstants.PREFIX + "/operate-log";

    @PostMapping(PREFIX + "/create")
    @Operation(summary = "创建操作日志")
    Result<Boolean> createOperateLog(@Valid @RequestBody OperateLogCreateDTO createReqDTO);

    @GetMapping(PREFIX + "/page")
    @Operation(summary = "获取指定模块的指定数据的操作日志分页")
    Result<PageResult<OperateLogVO>> getOperateLogPage(@SpringQueryMap OperateLogPageDTO pageReqDTO);
}
