package com.zp.gmall.framework.common.ddd;

/**
 * 值对象标记基类。
 * 值对象没有独立标识，通过其属性值来定义相等性。
 * 值对象必须是不可变的（immutable），可以自由共享。
 * 子类必须实现 equals() 和 hashCode()。
 */
public abstract class BaseValueObject {
    // 子类必须重写 equals 和 hashCode
}
