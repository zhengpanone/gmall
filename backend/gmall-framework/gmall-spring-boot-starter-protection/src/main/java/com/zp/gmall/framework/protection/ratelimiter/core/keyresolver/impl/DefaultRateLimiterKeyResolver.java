package com.zp.gmall.framework.protection.ratelimiter.core.keyresolver.impl;

import cn.hutool.crypto.SecureUtil;
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
 * Description: 默认（全局级别）限流Key解析器,使用方法名+方法参数组装成一个Key
 * 为避免Key过长，使用MD5进行压缩
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-07
 */
public class DefaultRateLimiterKeyResolver implements RateLimiterKeyResolver {
    @Override
    public String resolver(JoinPoint joinPoint, RateLimiter rateLimiter) {
        String methodName = joinPoint.getSignature().toLongString();
        String argsStr = StrUtils.joinMethodArgs(joinPoint);
        return SecureUtil.md5(methodName + argsStr);
    }
}
