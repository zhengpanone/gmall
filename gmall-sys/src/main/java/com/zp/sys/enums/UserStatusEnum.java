package com.zp.sys.enums;

/**
 * 用户状态
 */
public enum UserStatusEnum {
    DISABLE(0),

    ENABLED(1);

    private int value;

    UserStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
