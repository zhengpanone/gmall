package com.zp.gmall.module.trade.order.domain.model.order;

import com.zp.gmall.framework.common.ddd.Identifier;

import java.util.Objects;

/**
 * 订单ID - 值对象
 * 类型安全的订单标识符，避免 Long 基本类型依赖。
 */
public final class OrderId implements Identifier {

    private static final long serialVersionUID = 1L;

    private final Long value;

    public OrderId(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("订单ID不能为空且必须大于0");
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
        if (!(o instanceof OrderId other)) {
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
