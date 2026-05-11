package com.zp.gmall.framework.protection.ratelimiter.core.keyresolver.impl;

import com.zp.gmall.framework.common.util.string.StrUtils;
import com.zp.gmall.framework.protection.ratelimiter.core.annotation.RateLimiter;
import com.zp.gmall.framework.protection.ratelimiter.core.keyresolver.RateLimiterKeyResolver;
import org.aspectj.lang.JoinPoint;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.framework.protection.ratelimiter.core.keyresolver.impl
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-07
 */
public class UserRateLimiterKeyResolver implements RateLimiterKeyResolver {
    @Override
    public String resolver(JoinPoint joinPoint, RateLimiter rateLimiter) {
        String methodName = joinPoint.getSignature().toString();
        String argsStr = StrUtils.joinMethodArgs(joinPoint);
//        String userId = WebFrameworkUtils.get();
        return "";
    }
}
