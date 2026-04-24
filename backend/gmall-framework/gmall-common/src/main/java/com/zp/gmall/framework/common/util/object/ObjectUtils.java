package com.zp.gmall.framework.common.util.object;

import java.util.Arrays;

/**
 * @author : zhengpanone
 * Date : 2026/4/25 00:26
 * Version : v1.0.0
 * Description:
 */
public class ObjectUtils {

    @SafeVarargs
    public static <T> boolean equalsAny(T obj, T... array) {
        return Arrays.asList(array).contains(obj);
    }
}
