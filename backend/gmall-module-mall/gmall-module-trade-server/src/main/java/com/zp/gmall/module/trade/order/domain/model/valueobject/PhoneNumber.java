package com.zp.gmall.module.trade.order.domain.model.valueobject;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 手机号值对象（不可变）。
 * 自带格式校验，确保系统内使用的手机号始终合法。
 */
public final class PhoneNumber {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");

    private final String value;

    public PhoneNumber(String value) {
        if (value == null || !PHONE_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("手机号格式非法: " + value);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * 脱敏显示（138****1234）
     */
    public String mask() {
        if (value.length() == 11) {
            return value.substring(0, 3) + "****" + value.substring(7);
        }
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhoneNumber that)) {
            return false;
        }
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return mask();
    }
}
