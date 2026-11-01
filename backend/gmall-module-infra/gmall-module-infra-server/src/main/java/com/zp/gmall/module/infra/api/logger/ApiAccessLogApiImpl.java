package com.zp.gmall.module.infra.api.logger;

import com.zp.gmall.framework.common.biz.infra.logger.ApiAccessLogCommonApi;
import com.zp.gmall.framework.common.biz.infra.logger.dto.ApiAccessLogCreateReqDTO;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.infra.service.logger.IApiAccessLogService;
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
public class ApiAccessLogApiImpl implements ApiAccessLogCommonApi {

    @Resource
    private IApiAccessLogService apiAccessLogService;


    @Override
    public Result<Boolean> createApiAccessLog(ApiAccessLogCreateReqDTO dto) {
        apiAccessLogService.create(dto);
        return Result.ok(true);
    }
}
