package com.zp.gmall.framework.common.ddd;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 领域服务标记注解。
 * 领域服务用于处理跨聚合或跨实体的业务逻辑，
 * 这些逻辑不适合放在单个聚合根或实体内部。
 *
 * <p>使用时需要配合 Spring 的 {@code @Service} 注解一起使用，以确保被 Spring 扫描为 Bean。</p>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DomainService {
}
