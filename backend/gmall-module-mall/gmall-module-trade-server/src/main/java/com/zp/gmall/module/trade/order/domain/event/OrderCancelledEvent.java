package com.zp.gmall.module.trade.order.domain.event;

import com.zp.gmall.framework.common.ddd.DomainEvent;
import com.zp.gmall.module.trade.order.domain.model.order.OrderId;

import java.io.Serial;

/**
 * 订单已取消领域事件。
 * 在订单取消后发布，可触发库存回滚、优惠券返还等后续流程。
 */
public class OrderCancelledEvent extends DomainEvent {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private final Long orderId;

    /** 取消原因 */
    private final String reason;

    public OrderCancelledEvent(OrderId orderId, String reason) {
        this.orderId = orderId.getValue();
        this.reason = reason;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }
}
