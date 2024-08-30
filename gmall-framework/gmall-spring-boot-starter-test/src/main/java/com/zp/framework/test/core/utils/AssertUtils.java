package com.zp.framework.test.core.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 14:11
 * Version : v1.0.0
 * Description: 单元测试，assert 断言工具类
 */
public class AssertUtils {
    /**
     * 比对两个对象的属性是否一致
     *
     * 注意，如果 expected 存在的属性，actual 不存在的时候，会进行忽略
     *
     * @param expected 期望对象
     * @param actual 实际对象
     * @param ignoreFields 忽略的属性数组
     */
    public static void assertPojoEquals(Object expected, Object actual, String... ignoreFields) {
        Field[] expectedFields = ReflectUtil.getFields(expected.getClass());
        Arrays.stream(expectedFields).forEach(expectedField -> {
            // 忽略 jacoco 自动生成的 $jacocoData 属性的情况
            if (expectedField.isSynthetic()) {
                return;
            }
            // 如果是忽略的属性，则不进行比对
            if (ArrayUtil.contains(ignoreFields, expectedField.getName())) {
                return;
            }
            // 忽略不存在的属性
            Field actualField = ReflectUtil.getField(actual.getClass(), expectedField.getName());
            if (actualField == null) {
                return;
            }
            // 比对
            Assertions.assertEquals(
                    ReflectUtil.getFieldValue(expected, expectedField),
                    ReflectUtil.getFieldValue(actual, actualField),
                    String.format("Field(%s) 不匹配", expectedField.getName())
            );
        });
    }
}
