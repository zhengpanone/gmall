package com.zp.gmall.module.trade.order.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * 支付订单请求
 */
@Schema(description = "支付订单请求")
public class PayOrderRequest {

    @Schema(description = "支付流水号", required = true)
    @NotBlank(message = "支付流水号不能为空")
    private String paymentNo;

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }
}
