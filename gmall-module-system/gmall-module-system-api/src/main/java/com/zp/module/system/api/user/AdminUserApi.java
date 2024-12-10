package com.zp.module.system.api.user;

import com.zp.framework.common.pojo.Result;
import com.zp.module.system.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author : zhengpanone
 * Date : 2024/1/10 11:25
 * Version : v1.0.0
 
 */
@FeignClient(contextId = "adminUserApi", name = ApiConstants.NAME)
@Tag(name = "RPC服务-管理员用户")
public interface AdminUserApi {
    String PREFIX = ApiConstants.PREFIX + "/user";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "通过用户 ID 查询用户")
    @Parameter(name = "id", description = "用户编号", example = "1", required = true)
    Result<Object> getUser(@RequestParam("id") Long id);
}
