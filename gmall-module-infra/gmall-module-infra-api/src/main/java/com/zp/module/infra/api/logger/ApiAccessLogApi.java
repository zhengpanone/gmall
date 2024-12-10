package com.zp.module.infra.api.logger;

import com.zp.framework.common.pojo.Result;
import com.zp.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;
import com.zp.module.infra.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Author : zhengpanone
 * Date : 2023/11/14 22:36
 * Version : v1.0.0
 
 */
@FeignClient(contextId = "apiAccessLogApi",name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - API 访问日志")
public interface ApiAccessLogApi {
    String PREFIX = ApiConstants.PREFIX + "/api-access-log";

    @PostMapping(PREFIX + "/create")
    @Operation(summary = "创建API访问日志")
    Result<Boolean> createApiAccessLog(@Valid @RequestBody ApiAccessLogCreateReqDTO createReqDTO);
}
