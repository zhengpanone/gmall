package com.zp.gmall.framework.redis.core.aop;

import cn.hutool.core.collection.CollUtil;
import com.zp.gmall.framework.redis.core.annotation.BatchCacheEvict;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * {@link BatchCacheEvict} 的切面实现。
 *
 * <p>Spring 原生的 {@code @CacheEvict} 更适合一次清理一个 key，批量删除数据时如果想只清理指定 key，
 * 通常需要手动拿 {@link CacheManager} 操作缓存。这个切面把这类逻辑封装到注解里：
 * 方法成功返回后，根据注解中的 SpEL 表达式解析出一批缓存 key，并逐个从指定缓存中删除。</p>
 */
@Aspect
@RequiredArgsConstructor
public class BatchCacheEvictAspect {

    /**
     * 参数名发现器，用于让 SpEL 表达式支持 {@code #ids} 这类方法参数名。
     */
    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    /**
     * 懒加载 {@link CacheManager}，避免自动配置阶段因 CacheManager 尚未创建而导致启动失败。
     */
    private final ObjectProvider<CacheManager> cacheManagerProvider;

    /**
     * SpEL 解析器，用于解析 {@link BatchCacheEvict#keys()}。
     */
    private final ExpressionParser expressionParser = new SpelExpressionParser();

    /**
     * 方法成功执行后清理缓存。
     *
     * <p>只有业务方法正常返回时才清理缓存；如果方法抛异常，数据库通常没有完成变更，
     * 此时不清缓存更符合 Spring Cache 的默认语义。</p>
     *
     * @param joinPoint       当前被拦截的方法调用
     * @param batchCacheEvict 方法上的批量清理缓存注解
     * @param result          方法返回值，可在 SpEL 中通过 {@code #result} 使用
     */
    @AfterReturning(pointcut = "@annotation(batchCacheEvict)", returning = "result")
    public void evict(JoinPoint joinPoint, BatchCacheEvict batchCacheEvict, Object result) {
        CacheManager cacheManager = cacheManagerProvider.getIfAvailable();
        if (cacheManager == null) {
            return;
        }
        Collection<?> keys = parseKeys(joinPoint, batchCacheEvict.keys(), result);
        if (CollUtil.isEmpty(keys)) {
            return;
        }
        Set<String> cacheNames = resolveCacheNames(batchCacheEvict);
        if (CollUtil.isEmpty(cacheNames)) {
            return;
        }
        cacheNames.stream()
                .map(cacheManager::getCache)
                .filter(Objects::nonNull)
                .forEach(cache -> keys.stream().filter(Objects::nonNull).forEach(cache::evict));
    }

    /**
     * 解析需要清理的缓存名称。
     *
     * <p>同时支持 {@link BatchCacheEvict#value()}、{@link BatchCacheEvict#cacheNames()} 和
     * {@link BatchCacheEvict#cacheName()}，其中 {@code cacheName} 是为了兼容旧的单缓存写法。</p>
     */
    private Set<String> resolveCacheNames(BatchCacheEvict batchCacheEvict) {
        Set<String> cacheNames = new LinkedHashSet<>();
        addCacheNames(cacheNames, batchCacheEvict.value());
        addCacheNames(cacheNames, batchCacheEvict.cacheNames());
        addCacheName(cacheNames, batchCacheEvict.cacheName());
        return cacheNames;
    }

    /**
     * 添加多个缓存名称，并过滤空值。
     */
    private void addCacheNames(Set<String> cacheNames, String[] candidates) {
        if (candidates == null) {
            return;
        }
        for (String cacheName : candidates) {
            addCacheName(cacheNames, cacheName);
        }
    }

    /**
     * 添加单个缓存名称，并过滤空字符串。
     */
    private void addCacheName(Set<String> cacheNames, String cacheName) {
        if (cacheName != null && !cacheName.isBlank()) {
            cacheNames.add(cacheName);
        }
    }

    /**
     * 根据 SpEL 表达式解析出待删除的缓存 key 集合。
     *
     * <p>表达式结果可以是单个 key、数组、{@link Iterable} 或 {@link Collection}。
     * 返回集合使用 {@link LinkedHashSet} 去重，避免同一个 key 重复访问 Redis。</p>
     */
    private Collection<?> parseKeys(JoinPoint joinPoint, String keysExpression, Object result) {
        Object value = expressionParser.parseExpression(keysExpression)
                .getValue(buildEvaluationContext(joinPoint, result));
        if (value == null) {
            return Collections.emptyList();
        }
        if (value instanceof Collection<?> collection) {
            return new LinkedHashSet<>(collection);
        }
        if (value instanceof Iterable<?> iterable) {
            Collection<Object> resultKeys = new LinkedHashSet<>();
            iterable.forEach(resultKeys::add);
            return resultKeys;
        }
        if (value.getClass().isArray()) {
            int length = Array.getLength(value);
            Collection<Object> resultKeys = new LinkedHashSet<>(length);
            for (int i = 0; i < length; i++) {
                resultKeys.add(Array.get(value, i));
            }
            return resultKeys;
        }
        return Collections.singletonList(value);
    }

    /**
     * 构建 SpEL 上下文。
     *
     * <p>支持通过参数名访问方法参数，例如 {@code #ids}；也支持 Spring 常见的
     * {@code #p0}/{@code #a0} 参数别名。额外暴露 {@code #methodName}、{@code #target}
     * 和 {@code #result}，便于复杂场景按返回值或目标对象信息计算缓存 key。</p>
     */
    private EvaluationContext buildEvaluationContext(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        Method method = getSpecificMethod(signature.getMethod(), target);
        MethodBasedEvaluationContext context = new MethodBasedEvaluationContext(
                target, method, joinPoint.getArgs(), PARAMETER_NAME_DISCOVERER);
        context.setVariable("methodName", method.getName());
        context.setVariable("target", target);
        context.setVariable("result", result);
        return context;
    }

    /**
     * 获取代理背后的具体方法。
     *
     * <p>当方法定义在接口或父类上时，使用目标类上的实际方法可以提升参数名和注解元数据解析的准确性。</p>
     */
    private Method getSpecificMethod(Method method, Object target) {
        if (target == null) {
            return method;
        }
        return AopUtils.getMostSpecificMethod(method, target.getClass());
    }
}
