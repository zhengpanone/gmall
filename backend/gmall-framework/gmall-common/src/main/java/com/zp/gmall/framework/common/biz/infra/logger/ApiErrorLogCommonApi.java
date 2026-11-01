package com.zp.gmall.framework.common.biz.infra.logger;

import com.zp.gmall.framework.common.biz.infra.logger.dto.ApiErrorLogCreateReqDTO;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.framework.common.enums.RpcConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Author : zhengpanone
 * Date : 2023/11/16 17:27
 * Version : v1.0.0
 */
@FeignClient(contextId = "apiErrorLogApi", name = RpcConstants.INFRA_NAME)
@Tag(name = "RPC 服务 - API 异常日志")
public interface ApiErrorLogCommonApi {

    String PREFIX = RpcConstants.INFRA_PREFIX + "/api-error-log";

    @PostMapping(PREFIX + "/create")
    @Operation(summary = "创建API错误日志")
    Result<Boolean> createApiErrorLog(@Valid @RequestBody ApiErrorLogCreateReqDTO createReqDTO);

    /**
     * 【异步】创建 API 异常日志
     *
     * @param createDTO 异常日志 DTO
     */
    @Async
    default void createApiErrorLogAsync(ApiErrorLogCreateReqDTO createDTO) {
        createApiErrorLog(createDTO).checkError();
    }
}
