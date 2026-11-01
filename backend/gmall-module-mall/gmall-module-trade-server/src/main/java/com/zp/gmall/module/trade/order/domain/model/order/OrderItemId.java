package com.zp.gmall.module.trade.order.domain.model.order;

import com.zp.gmall.framework.common.ddd.Identifier;

import java.util.Objects;

/**
 * 订单项ID - 值对象
 */
public final class OrderItemId implements Identifier {

    private static final long serialVersionUID = 1L;

    private final Long value;

    public OrderItemId(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("订单项ID不能为空且必须大于0");
        }
        this.value = value;
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderItemId other)) {
            return false;
        }
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
