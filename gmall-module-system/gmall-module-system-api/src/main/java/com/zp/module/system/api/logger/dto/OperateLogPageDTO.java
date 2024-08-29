package com.zp.module.system.api.logger.dto;

import com.zp.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Author : zhengpanone
 * Date : 2024/8/5 17:45
 * Version : v1.0.0
 * Description: 操作日志分页 Request DTO
 */
@Schema(name = "RPC 服务 - 操作日志分页 Request DTO")
@Data
@EqualsAndHashCode(callSuper=false)
public class OperateLogPageDTO extends PageParam {
    @Schema(description = "模块类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "订单")
    private String type;

    @Schema(description = "模块数据编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "188")
    private Long bizId;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "666")
    private String userId;

}
