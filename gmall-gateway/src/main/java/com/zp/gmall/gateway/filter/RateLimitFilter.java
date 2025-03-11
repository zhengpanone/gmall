package com.zp.gmall.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Author : zhengpanone
 * Date : 2025/4/9 12:55
 * Version : v1.0.0
 * Description:
 */
@Component
public class RateLimitFilter implements GlobalFilter, Ordered {

    private final RedisTemplate<String, Object> redisTemplate;

    // 通过构造函数注入 RedisTemplate，用于存储和管理请求计数
    public RateLimitFilter(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取客户端 IP 作为限流的 key
        String key = "rate_limit:" + exchange.getRequest().getRemoteAddress();

        // 从 Redis 获取当前请求次数
        Integer current = (Integer) redisTemplate.opsForValue().get(key);

        // 如果当前请求次数小于 5，则允许请求通过，并增加计数
        if (current == null || current < 5) {
            redisTemplate.opsForValue().increment(key, 1);
            return chain.filter(exchange);
        }
        // 超过限制，返回 429 Too Many Requests
        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        // 过滤器优先级，值越小优先级越高
        return -1;
    }
}
