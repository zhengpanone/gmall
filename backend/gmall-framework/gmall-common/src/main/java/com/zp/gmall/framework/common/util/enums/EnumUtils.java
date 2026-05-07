package com.zp.gmall.framework.common.util.enums;

import com.zp.gmall.framework.common.core.Valuable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * 枚举工具类
 *
 * @author zp
 */
public final class EnumUtils {

    /**
     * 枚举缓存
     */
    private static final ConcurrentMap<String, Set<String>> ENUM_CACHE =
            new ConcurrentHashMap<>();

    private EnumUtils() {
    }

    /**
     * 获取枚举允许值
     *
     * @param enumClass  枚举类
     * @param ignoreCase 是否忽略大小写
     * @return 枚举值集合
     */
    public static Set<String> getEnumValues(
            Class<? extends Enum<?>> enumClass,
            boolean ignoreCase) {

        String cacheKey = buildCacheKey(enumClass, ignoreCase);

        return ENUM_CACHE.computeIfAbsent(
                cacheKey,
                key -> parseEnumValues(enumClass, ignoreCase)
        );
    }

    /**
     * 解析枚举值
     */
    private static Set<String> parseEnumValues(
            Class<? extends Enum<?>> enumClass,
            boolean ignoreCase) {

        Enum<?>[] enumConstants = enumClass.getEnumConstants();

        if (enumConstants == null || enumConstants.length == 0) {
            throw new IllegalArgumentException(
                    "枚举类没有定义枚举值: " + enumClass.getName()
            );
        }

        Set<String> values = Arrays.stream(enumConstants)
                .map(item -> {

                    Object value;

                    // 优先使用 Valuable
                    if (item instanceof Valuable) {
                        value = ((Valuable<?>) item).getValue();
                    } else {
                        value = item.name();
                    }

                    String str = String.valueOf(value);

                    return ignoreCase
                            ? str.toLowerCase()
                            : str;
                })
                .collect(Collectors.toSet());

        return Collections.unmodifiableSet(values);
    }

    /**
     * 构建缓存 Key
     */
    private static String buildCacheKey(
            Class<? extends Enum<?>> enumClass,
            boolean ignoreCase) {

        return enumClass.getName() + "#" + ignoreCase;
    }
}