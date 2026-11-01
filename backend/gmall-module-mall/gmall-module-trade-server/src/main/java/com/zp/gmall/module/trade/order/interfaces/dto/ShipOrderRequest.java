package com.zp.gmall.module.trade.order.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * 发货请求
 */
@Schema(description = "发货请求")
public class ShipOrderRequest {

    @Schema(description = "物流单号", required = true)
    @NotBlank(message = "物流单号不能为空")
    private String trackingNo;

    @Schema(description = "物流公司", example = "顺丰速运")
    private String logisticsCompany;

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }
}
