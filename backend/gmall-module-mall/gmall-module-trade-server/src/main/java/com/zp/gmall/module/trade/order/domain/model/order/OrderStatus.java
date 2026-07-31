package com.zp.gmall.module.trade.order.domain.model.order;

/**
 * 订单状态枚举。
 * 严格定义订单生命周期中的合法状态，防止非法状态迁移。
 *
 * <pre>
 * 状态流转图:
 * PENDING_PAYMENT ──支付──> PAID ──发货──> SHIPPED ──签收──> DELIVERED
 *       │                    │                   │
 *       └──取消──> CANCELLED ◄──取消──┘         └──拒收──> REFUNDING ──退款──> REFUNDED
 * </pre>
 */
public enum OrderStatus {

    /** 待支付 */
    PENDING_PAYMENT("待支付"),

    /** 已支付 */
    PAID("已支付"),

    /** 已发货 */
    SHIPPED("已发货"),

    /** 已签收 */
    DELIVERED("已签收"),

    /** 已取消 */
    CANCELLED("已取消"),

    /** 退款中 */
    REFUNDING("退款中"),

    /** 已退款 */
    REFUNDED("已退款");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 判断是否可以取消订单
     */
    public boolean canCancel() {
        return this == PENDING_PAYMENT || this == PAID;
    }

    /**
     * 判断是否可以支付
     */
    public boolean canPay() {
        return this == PENDING_PAYMENT;
    }

    /**
     * 判断是否可以发货
     */
    public boolean canShip() {
        return this == PAID;
    }

    /**
     * 判断是否为终态
     */
    public boolean isFinal() {
        return this == DELIVERED || this == CANCELLED || this == REFUNDED;
    }
}
