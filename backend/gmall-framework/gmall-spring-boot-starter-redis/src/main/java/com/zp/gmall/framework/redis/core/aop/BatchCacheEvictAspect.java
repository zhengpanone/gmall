package com.zp.gmall.framework.redis.core.aop;

import cn.hutool.core.collection.CollUtil;
import com.zp.gmall.framework.redis.core.annotation.BatchCacheEvict;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * {@link BatchCacheEvict} 的切面实现。
 */
@Aspect
@RequiredArgsConstructor
public class BatchCacheEvictAspect {

    private final CacheManager cacheManager;

    private final ExpressionParser expressionParser = new SpelExpressionParser();

    @AfterReturning("@annotation(batchCacheEvict)")
    public void evict(JoinPoint joinPoint, BatchCacheEvict batchCacheEvict) {
        Collection<?> keys = parseKeys(joinPoint, batchCacheEvict.keys());
        if (CollUtil.isEmpty(keys)) {
            return;
        }
        Cache cache = cacheManager.getCache(batchCacheEvict.cacheName());
        if (cache == null) {
            return;
        }
        keys.stream().filter(Objects::nonNull).forEach(cache::evict);
    }

    private Collection<?> parseKeys(JoinPoint joinPoint, String keysExpression) {
        Object value = expressionParser.parseExpression(keysExpression)
                .getValue(buildEvaluationContext(joinPoint));
        if (value == null) {
            return Collections.emptyList();
        }
        if (value instanceof Collection<?> collection) {
            return collection;
        }
        if (value.getClass().isArray()) {
            int length = Array.getLength(value);
            Collection<Object> result = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                result.add(Array.get(value, i));
            }
            return result;
        }
        return Collections.singletonList(value);
    }

    private EvaluationContext buildEvaluationContext(JoinPoint joinPoint) {
        StandardEvaluationContext context = new StandardEvaluationContext();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        String[] parameterNames = signature.getParameterNames();
        for (int i = 0; i < args.length; i++) {
            context.setVariable("p" + i, args[i]);
            context.setVariable("a" + i, args[i]);
            if (parameterNames != null && i < parameterNames.length) {
                context.setVariable(parameterNames[i], args[i]);
            }
        }
        context.setVariable("methodName", method.getName());
        return context;
    }
}
