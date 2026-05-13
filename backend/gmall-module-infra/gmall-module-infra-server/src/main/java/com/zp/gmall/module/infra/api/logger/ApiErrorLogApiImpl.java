package com.zp.gmall.module.infra.api.logger;

import com.zp.gmall.framework.common.biz.infra.logger.ApiErrorLogCommonApi;
import com.zp.gmall.framework.common.biz.infra.logger.dto.ApiErrorLogCreateReqDTO;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.infra.service.logger.IApiErrorLogService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-13
 */
@RestController
@Validated
public class ApiErrorLogApiImpl implements ApiErrorLogCommonApi {

    @Resource
    private IApiErrorLogService apiErrorLogService;

    @Override
    public Result<Boolean> createApiErrorLog(ApiErrorLogCreateReqDTO dto) {
        apiErrorLogService.create(dto);
        return Result.ok(true);
    }
}
