package com.zp.framework.common.enums;

import cn.hutool.core.util.ArrayUtil;
import com.zp.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Author : zhengpanone
 * Date : 2023/11/13 23:20
 * Version : v1.0.0
 * Description: 全局用户类型枚举
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum implements IntArrayValuable {
    MEMBER(1, "会员"), // 面向c端,普通用户
    ADMIN(2, "管理员"), // 面向b端,管理后台
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(UserTypeEnum::getValue).toArray();
    /**
     * 类型
     */
    private final Integer value;
    /**
     * 类型名
     */
    private final String name;

    public static UserTypeEnum valueOf(Integer value) {
        return ArrayUtil.firstMatch(userType -> userType.getValue().equals(value), UserTypeEnum.values());
    }

    /**
     * @return int数组
     */
    @Override
    public int[] array() {
        return ARRAYS;
    }
}
