package com.zp.framework.core.aop;

import cn.hutool.core.util.ArrayUtil;
import com.zp.framework.common.enums.UserTypeEnum;
import com.zp.framework.core.service.OperateLog;
import com.zp.framework.core.service.OperateLogFrameworkService;
import com.zp.framework.web.core.util.WebFrameworkUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * Author : zhengpanone
 * Date : 2023/12/17 12:09
 * Version : v1.0.0
 * Description: 拦截使用@OperateLog注解，如果满足条件，则生成操作日志
 * 满足如下任一条件，则会进行记录：
 * 1. 使用 @ApiOperation + 非 @GetMapping
 * 2. 使用 @OperateLog 注解
 * 但是，如果声明 @OperateLog 注解时，将 enable 属性设置为 false 时，强制不记录。
 */
@Aspect
@Slf4j
public class OperateLogAspect {
    /**
     * 用于记录操作内容的上下文
     *
     * @see OperateLog#getContent()
     */
    private static final ThreadLocal<String> CONTENT = new ThreadLocal<>();
    /**
     * 用于记录拓展字段的上下文
     *
     * @see OperateLog#getExts()
     */
    private static final ThreadLocal<Map<String, Object>> EXTS = new ThreadLocal<>();
    @Resource
    private OperateLogFrameworkService operateLogFrameworkService;

    @Around("@annotation(operation)")
    public Object around(ProceedingJoinPoint joinPoint, Operation operation) throws Throwable {
        com.zp.framework.core.annotations.OperateLog operateLog = getMethodAnnotation(joinPoint, com.zp.framework.core.annotations.OperateLog.class);
        return around0(joinPoint, operateLog, operation);
    }

    private Object around0(ProceedingJoinPoint joinPoint,
                           com.zp.framework.core.annotations.OperateLog operateLog,
                           Operation operation) throws Throwable {
        // 目前，只有管理员，才记录操作日志！所以非管理员，直接调用，不进行记录
        Integer userType = WebFrameworkUtils.getLoginUserType();
        if (Objects.equals(userType, UserTypeEnum.ADMIN.getValue())) {
            return joinPoint.proceed();
        }
        // 记录开始时间
        LocalDateTime startTime = LocalDateTime.now();
        try {
            // 执行原有方法
            Object result = joinPoint.proceed();
            // this.log

        } catch (Throwable exception) {
            throw exception;
        } finally {
            // TODO
            return null;
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends Annotation> T getMethodAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationClass) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(annotationClass);
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends Annotation> T getClassAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationClass) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass().getAnnotation(annotationClass);
    }

    private void log(ProceedingJoinPoint joinPoint,
                     com.zp.framework.core.annotations.OperateLog operateLog,
                     Operation operation,
                     LocalDateTime startTime,
                     Object result,
                     Throwable exception) {
        try {
            // 判断不记录的情况
            if (!isLogEnable(joinPoint, operateLog)) {
                return;
            }
        } catch (Throwable ex) {

        }
    }

    private static boolean isLogEnable(ProceedingJoinPoint joinPoint,
                                       com.zp.framework.core.annotations.OperateLog operateLog) {
        // 有@OperateLog注解的情况下
        if (operateLog != null) {
            return operateLog.enable();
        }
        // Cloud 专属逻辑：如果是 RPC 请求，则必须 @OperateLog 注解，才会记录操作日志
        String className = joinPoint.getSignature().getDeclaringType().getName();
        if (WebFrameworkUtils.isRpcRequest(className)) {
            return false;
        }
        // 没有 @ApiOperation 注解的情况下，只记录 POST、PUT、DELETE 的情况
        return obtainFirstLogRequestMethod(obtainRequestMethod(joinPoint)) != null;
    }

    private static RequestMethod obtainFirstLogRequestMethod(RequestMethod[] requestMethods) {
        if (ArrayUtil.isEmpty(requestMethods)) {
            return null;
        }
        return Arrays.stream(requestMethods).filter(requestMethod ->
                        requestMethod == RequestMethod.POST
                                || requestMethod == RequestMethod.PUT
                                || requestMethod == RequestMethod.DELETE)
                .findFirst().orElse(null);
    }

    private static RequestMethod[] obtainRequestMethod(ProceedingJoinPoint joinPoint) {
        RequestMapping requestMapping = AnnotationUtils.getAnnotation(((MethodSignature) joinPoint.getSignature()).getMethod(), RequestMapping.class);
        return requestMapping != null ? requestMapping.method() : new RequestMethod[]{};
    }
}
