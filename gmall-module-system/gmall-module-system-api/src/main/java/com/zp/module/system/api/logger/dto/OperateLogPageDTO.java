package com.zp.module.system.api.logger.dto;

import com.zp.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.zp.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * Author : zhengpanone
 * Date : 2024/8/5 17:45
 * Version : v1.0.0
 * Description: 操作日志分页 Request DTO
 */
@Schema(name = "RPC 服务 - 操作日志分页 Request DTO")
@Data
@EqualsAndHashCode(callSuper = false)
public class OperateLogPageDTO extends PageParam {

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "666")
    private String userId;

    @Schema(description = "操作模块业务编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "188")
    private Long bizId;


    @Schema(description = "模块类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "订单")
    private String type;

    @Schema(description = "操作名，模拟匹配", example = "创建订单")
    private String subType;

    @Schema(description = "操作明细，模拟匹配", example = "修改编号为 1 的用户信息")
    private String action;

    @Schema(description = "开始时间", example = "[2022-07-01 00:00:00,2022-07-01 23:59:59]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;
}
