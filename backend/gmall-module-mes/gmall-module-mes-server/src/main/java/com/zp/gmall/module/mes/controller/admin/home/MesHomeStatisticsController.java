package com.zp.gmall.module.mes.controller.admin.home;

import com.alibaba.nacos.api.model.v2.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "管理后台 - MES 首页统计")
@RestController
@RequestMapping("/home-statistics")
@Validated
public class MesHomeStatisticsController {

    @GetMapping("/summary")
    @Operation(summary = "获得首页汇总统计")
    public Result<?> getHomeSummary() {
        return Result.success();
    }
}
