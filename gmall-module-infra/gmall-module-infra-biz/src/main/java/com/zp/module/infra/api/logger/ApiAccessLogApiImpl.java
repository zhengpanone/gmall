package com.zp.module.infra.api.logger;

import com.zp.framework.common.pojo.Result;
import com.zp.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;
import com.zp.module.infra.service.logger.ApiAccessLogService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : zhengpanone
 * Date : 2024/1/9 17:19
 * Version : v1.0.0
 
 */
@RestController // 提供RESTFul Api 接口给Feign调用
@Validated
public class ApiAccessLogApiImpl implements ApiAccessLogApi {
    @Resource
    private ApiAccessLogService apiAccessLogService;

    @Override
    public Result<Boolean> createApiAccessLog(ApiAccessLogCreateReqDTO createReqDTO) {
        apiAccessLogService.createApiAccessLog(createReqDTO);
        return Result.ok(true);
    }
}
