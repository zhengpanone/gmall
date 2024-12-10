package com.zp.module.infra.api.config;

import com.zp.framework.common.pojo.Result;
import com.zp.module.infra.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author : zhengpanone
 * Date : 2024/8/6 16:20
 * Version : v1.0.0
 * Description:
 */
@FeignClient(contextId = "configApi", name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - 参数配置")
public interface ConfigApi {

    String PREFIX = ApiConstants.PREFIX + "/config";

    @GetMapping(PREFIX + "/get-value-by-key")
    @Operation(summary = "根据参数键查询参数值")
    Result<String> getConfigValueByKey(@RequestParam("key") String key);

}

