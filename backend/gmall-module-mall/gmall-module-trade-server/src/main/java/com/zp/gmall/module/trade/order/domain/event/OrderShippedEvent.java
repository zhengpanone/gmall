package com.zp.gmall.module.trade.order.domain.event;

import com.zp.gmall.framework.common.ddd.DomainEvent;
import com.zp.gmall.module.trade.order.domain.model.order.OrderId;

import java.io.Serial;

/**
 * 订单已发货领域事件。
 * 在订单发货后发布，可触发物流追踪、客户通知等后续流程。
 */
public class OrderShippedEvent extends DomainEvent {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private final Long orderId;

    /** 物流单号 */
    private final String trackingNo;

    /** 物流公司 */
    private final String logisticsCompany;

    public OrderShippedEvent(OrderId orderId, String trackingNo, String logisticsCompany) {
        this.orderId = orderId.getValue();
        this.trackingNo = trackingNo;
        this.logisticsCompany = logisticsCompany;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }
}
