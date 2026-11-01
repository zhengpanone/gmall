package com.zp.gmall.framework.apilog.config;

import com.zp.gmall.framework.common.biz.infra.logger.ApiAccessLogCommonApi;
import com.zp.gmall.framework.common.biz.infra.logger.ApiErrorLogCommonApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-13
 */
@AutoConfiguration
@EnableFeignClients(clients = {ApiAccessLogCommonApi.class, ApiErrorLogCommonApi.class})
public class GmallApiLogRpcAutoConfiguration {
}
