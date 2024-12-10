package com.zp.module.member.api.user;

import com.zp.framework.common.pojo.Result;
import com.zp.module.member.api.user.dto.MemberUserVO;
import com.zp.module.member.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author : zhengpanone
 * Date : 2024/7/31 16:51
 * Version : v1.0.0
 * Description:
 */
@FeignClient(contextId = "memberUserApi", name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - 会员用户")
public interface MemberUserApi {

    String PREFIX = ApiConstants.PREFIX + "/user";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "获得会员用户信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    Result<MemberUserVO> getUser(@RequestParam("id") String id);
}
