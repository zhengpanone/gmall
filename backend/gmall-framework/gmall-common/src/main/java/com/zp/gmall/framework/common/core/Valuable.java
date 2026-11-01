package com.zp.gmall.framework.common.core;

/**
 * 可获取值接口
 *
 * @param <T> 值类型
 */
public interface Valuable<T> {

    /**
     * 获取值
     *
     * @return 值
     */
    T getValue();

}