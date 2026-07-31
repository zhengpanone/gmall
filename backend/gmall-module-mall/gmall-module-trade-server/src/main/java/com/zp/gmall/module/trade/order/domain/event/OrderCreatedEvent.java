package com.zp.gmall.module.trade.order.domain.event;

import com.zp.gmall.framework.common.ddd.DomainEvent;
import com.zp.gmall.module.trade.order.domain.model.order.OrderId;
import com.zp.gmall.module.trade.order.domain.model.valueobject.Money;

import java.io.Serial;

/**
 * 订单已创建领域事件。
 * 在订单成功创建后发布，可触发库存扣减、优惠券核销等后续流程。
 */
public class OrderCreatedEvent extends DomainEvent {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private final Long orderId;

    /** 用户ID */
    private final Long memberId;

    /** 订单金额 */
    private final String totalAmount;

    public OrderCreatedEvent(OrderId orderId, Long memberId, Money totalAmount) {
        this.orderId = orderId.getValue();
        this.memberId = memberId;
        this.totalAmount = totalAmount.getAmount().toPlainString();
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }
}
