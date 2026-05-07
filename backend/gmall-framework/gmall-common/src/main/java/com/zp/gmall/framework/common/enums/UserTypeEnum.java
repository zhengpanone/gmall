package com.zp.gmall.framework.common.enums;

import com.zp.gmall.framework.common.core.Valuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 全局用户类型枚举
 */
@AllArgsConstructor
@Getter
public enum UserTypeEnum implements Valuable<String> {

    MEMBER("1", "会员"), // 面向 c 端，普通用户
    ADMIN("2", "管理员"); // 面向 b 端，管理后台

    public static final String[] ARRAYS = Arrays.stream(values()).map(UserTypeEnum::getValue).toArray(String[]::new);

    /**
     * 类型
     */
    private final String value;
    /**
     * 类型名
     */
    private final String name;

    @Override
    public String getValue() {
        return value;
    }
}
