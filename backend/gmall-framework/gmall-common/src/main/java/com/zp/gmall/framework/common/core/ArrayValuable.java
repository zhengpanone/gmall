package com.zp.gmall.framework.common.core;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 01:13
 * Version : v1.0.0
 * Description: 可生成 T 数组的接口
 */
public interface ArrayValuable<T> {
    /**
     * 生成 T 数组
     *
     * @return T 数组
     */
    T[] array();
}
