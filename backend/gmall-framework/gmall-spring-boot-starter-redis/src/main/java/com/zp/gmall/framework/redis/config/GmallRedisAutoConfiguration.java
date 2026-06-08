package com.zp.gmall.framework.redis.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.zp.gmall.framework.redis.core.aop.BatchCacheEvictAspect;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Author : zhengpanone
 * Date : 2023/11/11 18:20
 * Version : v1.0.0
 * Description: Redis配置类
 */
@EnableCaching
@AutoConfiguration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@AutoConfigureBefore(CacheAutoConfiguration.class)
@ConditionalOnClass({RedisTemplate.class, RedisConnectionFactory.class, CacheManager.class})
@EnableConfigurationProperties(CacheProperties.class)
public class GmallRedisAutoConfiguration {

    /**
     * 创建 RedisTemplate Bean，统一使用字符串 key 和 JSON value。
     *
     * @param factory Redis连接工厂
     * @return RedisTemplate
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    @ConditionalOnSingleCandidate(RedisConnectionFactory.class)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory,
                                                       RedisSerializer<Object> redisJsonSerializer) {
        // 创建RedisTemplate对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置RedisConnection工厂。它就是实现多种 Java Redis 客户端接入的秘密工厂。
        template.setConnectionFactory(factory);
        // 使用String序列化方式，序列化Key
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        template.setValueSerializer(redisJsonSerializer);
        template.setHashValueSerializer(redisJsonSerializer);
        template.setDefaultSerializer(redisJsonSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 配置 Spring Cache 使用 Redis 时的序列化方式。
     *
     * <p>Spring Boot 默认使用 JDK 序列化，Redis 中不可读且容易受类结构变化影响。这里改为字符串 key +
     * JSON value，同时保留 {@code spring.cache.redis.*} 的 TTL、key 前缀等配置。</p>
     */
    @Bean
    @ConditionalOnMissingBean
    public RedisCacheConfiguration redisCacheConfiguration(
            CacheProperties cacheProperties, RedisSerializer<Object> redisJsonSerializer) {
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        RedisCacheConfiguration config =
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisJsonSerializer));

        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        return config;
    }

    @Bean
    @ConditionalOnMissingBean
    public BatchCacheEvictAspect batchCacheEvictAspect(ObjectProvider<CacheManager> cacheManagerProvider) {
        return new BatchCacheEvictAspect(cacheManagerProvider);
    }

    /**
     * Redis JSON 序列化器。
     *
     * <p>缓存方法返回值类型不固定，例如 RoleDO、ConfigVO、OAuth2ClientDO，所以需要保留类型信息用于反序列化。</p>
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisJsonSerializer")
    public RedisSerializer<Object> redisJsonSerializer(ObjectMapper objectMapper) {
        ObjectMapper redisObjectMapper = objectMapper.copy();
        redisObjectMapper.activateDefaultTypingAsProperty(
                BasicPolymorphicTypeValidator.builder()
                        .allowIfSubType("com.zp.gmall.")
                        .allowIfSubType("java.util.")
                        .allowIfSubType("java.time.")
                        .allowIfSubType("org.springframework.cache.support.NullValue")
                        .build(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.Id.CLASS.getDefaultPropertyName());
        GenericJackson2JsonRedisSerializer.registerNullValueSerializer(redisObjectMapper,
                JsonTypeInfo.Id.CLASS.getDefaultPropertyName());
        return new GenericJackson2JsonRedisSerializer(redisObjectMapper);
    }
}
