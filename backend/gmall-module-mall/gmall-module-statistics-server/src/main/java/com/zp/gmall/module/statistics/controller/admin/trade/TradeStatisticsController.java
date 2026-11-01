package com.zp.gmall.module.statistics.controller.admin.trade;

import com.alibaba.nacos.api.model.v2.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 00:29
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "管理后台 - 交易统计")
@RestController
@RequestMapping("/trade")
@Validated
@Slf4j
public class TradeStatisticsController {

    @GetMapping("/summary")
    @Operation(summary = "获得交易统计")
    public Result<?> getTradeSummaryComparison() {

        return Result.success();
    }

    @GetMapping("/analyse")
    @Operation(summary = "获得交易状况统计")
    public Result<?> getTradeStatisticsAnalyse() {
        return Result.success();
    }
}
