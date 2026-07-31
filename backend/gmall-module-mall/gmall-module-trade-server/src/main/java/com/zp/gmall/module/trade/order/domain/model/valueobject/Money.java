package com.zp.gmall.module.trade.order.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

/**
 * 金额值对象（不可变）。
 * 封装金额与货币，提供类型安全的货币运算，避免 BigDecimal 裸用。
 */
@Getter
@AllArgsConstructor
public final class Money {

    /** 人民币 */
    public static final Currency CNY = Currency.getInstance("CNY");

    private final BigDecimal amount;
    private final Currency currency;

    /**
     * 创建人民币金额
     */
    public static Money cny(BigDecimal amount) {
        return new Money(amount.setScale(2, RoundingMode.HALF_UP), CNY);
    }

    /**
     * 创建人民币金额
     */
    public static Money cny(String amount) {
        return cny(new BigDecimal(amount));
    }

    /**
     * 创建人民币金额
     */
    public static Money cny(long amount) {
        return cny(BigDecimal.valueOf(amount));
    }

    /**
     * 零元
     */
    public static Money zero() {
        return cny(BigDecimal.ZERO);
    }

    /**
     * 加法
     */
    public Money add(Money other) {
        assertSameCurrency(other);
        return new Money(this.amount.add(other.amount), this.currency);
    }

    /**
     * 减法
     */
    public Money subtract(Money other) {
        assertSameCurrency(other);
        return new Money(this.amount.subtract(other.amount), this.currency);
    }

    /**
     * 乘以数量
     */
    public Money multiply(int multiplier) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(multiplier))
                .setScale(2, RoundingMode.HALF_UP), this.currency);
    }

    /**
     * 比较是否大于
     */
    public boolean isGreaterThan(Money other) {
        assertSameCurrency(other);
        return this.amount.compareTo(other.amount) > 0;
    }

    /**
     * 比较是否大于等于
     */
    public boolean isGreaterThanOrEqual(Money other) {
        assertSameCurrency(other);
        return this.amount.compareTo(other.amount) >= 0;
    }

    /**
     * 比较是否等于
     */
    public boolean isEqual(Money other) {
        return this.equals(other);
    }

    private void assertSameCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException(
                    String.format("货币类型不匹配: %s vs %s", this.currency, other.currency));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money other)) {
            return false;
        }
        return Objects.equals(amount, other.amount)
                && Objects.equals(currency, other.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return currency.getSymbol() + amount.toPlainString();
    }
}
