package com.zp.gmall.framework.protection.ratelimiter.core.keyresolver;

import com.zp.gmall.framework.protection.ratelimiter.core.annotation.RateLimiter;
import org.aspectj.lang.JoinPoint;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.framework.protection.ratelimiter.core.keyresolver
 * <p>
 * Description: 限流Key解析器
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-07
 */
public interface RateLimiterKeyResolver {

    /**
     * 解析一个key
     *
     * @param joinPoint   限流注解
     * @param rateLimiter AOP切面
     * @return key
     */
    String resolver(JoinPoint joinPoint, RateLimiter rateLimiter);
}
