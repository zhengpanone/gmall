package com.zp.module.infra.api.logger;

import com.zp.framework.common.pojo.Result;
import com.zp.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import com.zp.module.infra.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Author : zhengpanone
 * Date : 2023/11/16 17:27
 * Version : v1.0.0
 
 */
@FeignClient(name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - API 异常日志")
public interface ApiErrorLogApi {
    String PREFIX = ApiConstants.PREFIX + "/api-error-log";
    @PostMapping(PREFIX + "/create")
    @Operation(summary = "创建API错误日志")
    Result<Boolean> createApiErrorLog(@Valid @RequestBody ApiErrorLogCreateReqDTO createReqDTO);
}
