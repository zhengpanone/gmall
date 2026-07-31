package com.zp.gmall.module.trade.order.domain.event;

import com.zp.gmall.framework.common.ddd.DomainEvent;
import com.zp.gmall.module.trade.order.domain.model.order.OrderId;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 订单已支付领域事件。
 * 在订单支付完成后发布，可触发发货、积分累积等后续流程。
 */
public class OrderPaidEvent extends DomainEvent {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private final Long orderId;

    /** 支付时间 */
    private final LocalDateTime paidTime;

    /** 支付流水号 */
    private final String paymentNo;

    public OrderPaidEvent(OrderId orderId, LocalDateTime paidTime, String paymentNo) {
        this.orderId = orderId.getValue();
        this.paidTime = paidTime;
        this.paymentNo = paymentNo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public LocalDateTime getPaidTime() {
        return paidTime;
    }

    public String getPaymentNo() {
        return paymentNo;
    }
}
