package com.zp.gmall.framework.common.ddd;

import java.io.Serializable;

/**
 * 领域标识符抽象基类。
 * 替代直接使用 Long/String 作为 ID，提供类型安全的值对象语义。
 * 每个限界上下文应定义自己的标识符类型，避免原始类型依赖。
 */
public interface Identifier extends Serializable {

    /**
     * 获取标识符的原始值
     */
    Object getValue();
}
