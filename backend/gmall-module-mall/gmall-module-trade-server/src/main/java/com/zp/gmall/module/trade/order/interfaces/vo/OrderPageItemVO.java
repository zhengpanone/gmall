package com.zp.gmall.module.trade.order.interfaces.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * 订单分页项 VO。
 */
@Schema(description = "订单分页项")
public class OrderPageItemVO {

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "订单编号")
    private String orderNo;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "状态描述")
    private String statusDesc;

    @Schema(description = "实付金额")
    private String payAmount;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getStatusDesc() { return statusDesc; }
    public void setStatusDesc(String statusDesc) { this.statusDesc = statusDesc; }
    public String getPayAmount() { return payAmount; }
    public void setPayAmount(String payAmount) { this.payAmount = payAmount; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}
