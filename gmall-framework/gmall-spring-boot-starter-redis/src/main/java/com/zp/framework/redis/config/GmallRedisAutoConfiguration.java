package com.zp.framework.redis.config;

import cn.hutool.core.util.ReflectUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Author : zhengpanone
 * Date : 2023/11/11 18:20
 * Version : v1.0.0
 * Description: Redis配置类
 */
@AutoConfiguration
public class GmallRedisAutoConfiguration {
    /**
     * 创建RedisTemplate Bean,使用JSON序列化
     *
     * @param factory Redis连接工厂
     * @return RedisTemplate
     */
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 创建RedisTemplate对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置RedisConnection工厂。它就是实现多种 Java Redis 客户端接入的秘密工厂。
        template.setConnectionFactory(factory);
        // 使用String序列化方式，序列化Key
        template.setKeySerializer(RedisSerializer.string());
        template.setHashValueSerializer(buildRedisSerializer());
        return template;
    }

    public static RedisSerializer<?> buildRedisSerializer() {
        RedisSerializer<Object> json = RedisSerializer.json();
        // 解决LocalDateTime的序列化
        ObjectMapper objectMapper = (ObjectMapper) ReflectUtil.getFieldValue(json, "mapper");
        objectMapper.registerModule(new JavaTimeModule());
        return json;
    }
}
