package com.zp.framework.security.core.aop;

import com.zp.framework.security.core.annotations.PreAuthenticated;
import com.zp.framework.security.core.util.SecurityFrameworkUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import static com.zp.framework.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;
import static com.zp.framework.common.exception.util.ServiceExceptionUtils.exception;

/**
 * Author : zhengpanone
 * Date : 2024/1/10 14:14
 * Version : v1.0.0
 */
@Aspect
@Slf4j
public class PreAuthenticatedAspect {
    @Around("@annotation(preAuthenticated)")
    public Object around(ProceedingJoinPoint joinPoint, PreAuthenticated preAuthenticated) throws Throwable {
        if (SecurityFrameworkUtils.getLoginUser() == null) {
            throw exception(UNAUTHORIZED);
        }
        return joinPoint.proceed();
    }
}
