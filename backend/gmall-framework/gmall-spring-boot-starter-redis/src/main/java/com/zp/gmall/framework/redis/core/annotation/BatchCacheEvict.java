package com.zp.gmall.framework.redis.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 批量清理 Spring Cache 缓存。
 *
 * <p>适用于一个方法需要删除多个缓存 key 的场景，例如批量删除数据后清理对应的缓存。
 * keys 使用 SpEL 表达式，表达式结果可以是单个 key、数组或集合。</p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BatchCacheEvict {

    /**
     * 缓存名称。
     */
    String cacheName();

    /**
     * 缓存 key 的 SpEL 表达式，例如 {@code #ids.ids} 或 {@code #ids?.ids}。
     */
    String keys();
}
