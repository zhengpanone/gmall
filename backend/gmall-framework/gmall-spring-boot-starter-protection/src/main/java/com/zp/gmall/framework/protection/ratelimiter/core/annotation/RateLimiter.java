package com.zp.gmall.framework.protection.ratelimiter.core.annotation;

import com.zp.gmall.framework.common.exception.enums.GlobalErrorCodeConstants;
import com.zp.gmall.framework.protection.ratelimiter.core.keyresolver.RateLimiterKeyResolver;
import com.zp.gmall.framework.protection.ratelimiter.core.keyresolver.impl.DefaultRateLimiterKeyResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 限流注解
 *
 * @author zhengpan
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {

    /**
     * 限流时间，单位秒,默认1秒
     */
    int time() default 1;

    /**
     * 时间单位，默认秒
     *
     * @return 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 限流次数，默认1次
     */
    int count() default 100;

    /**
     * 限流提示信息
     *
     * @see GlobalErrorCodeConstants#TOO_MANY_REQUESTS
     * 为空时，使用 TOO_MANY_REQUESTS 错误提示
     */
    String message() default "";

    /**
     * 使用的key解析器
     *
     * @return
     */
    Class<? extends RateLimiterKeyResolver> keyResolver() default DefaultRateLimiterKeyResolver.class;

    /**
     * 使用的key解析器的参数
     *
     * @return key解析器的参数
     */
    String keyArg() default "";
}
