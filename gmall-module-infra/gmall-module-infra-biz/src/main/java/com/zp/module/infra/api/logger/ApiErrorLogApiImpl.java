package com.zp.module.infra.api.logger;

import com.zp.framework.common.pojo.Result;
import com.zp.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import com.zp.module.infra.service.logger.ApiErrorLogService;
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
public class ApiErrorLogApiImpl implements ApiErrorLogApi {
    @Resource
    private ApiErrorLogService apiErrorLogService;


    @Override
    public Result<Boolean> createApiErrorLog(ApiErrorLogCreateReqDTO createReqDTO) {
        apiErrorLogService.createApiErrorLog(createReqDTO);
        return Result.ok(true);
    }
}
