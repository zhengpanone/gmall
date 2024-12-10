package com.zp.module.system.api.logger;

import com.zp.framework.common.pojo.Result;
import com.zp.module.system.api.logger.dto.LoginLogCreateDTO;
import com.zp.module.system.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Author : zhengpanone
 * Date : 2024/8/6 10:30
 * Version : v1.0.0
 * Description: RPC 服务 - 登录日志
 */
@FeignClient(contextId = "loginLogApi", name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - 登录日志")
public interface LoginLogApi {
    String PREFIX = ApiConstants.PREFIX + "/login-log";

    @PostMapping(PREFIX + "/create")
    @Operation(summary = "创建登录日志")
    Result<Boolean> createLoginLog(@Valid @RequestBody LoginLogCreateDTO reqDTO);
}
