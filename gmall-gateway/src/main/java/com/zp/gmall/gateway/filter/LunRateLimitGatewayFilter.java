package com.zp.gmall.gateway.filter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.http.HttpStatus;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/4/9 18:58
 * Version : v1.0.0
 * Description:
 */
@Component
public class LunRateLimitGatewayFilter extends AbstractGatewayFilterFactory<LunRateLimitGatewayFilter.Config> {

    private final ReactiveStringRedisTemplate redisTemplate;
    private final DefaultRedisScript<List> rateLimiterScript;

    public LunRateLimitGatewayFilter(ReactiveStringRedisTemplate redisTemplate) {
        super(Config.class);
        this.redisTemplate = redisTemplate;

        this.rateLimiterScript = new DefaultRedisScript<>();
        this.rateLimiterScript.setScriptSource(
                new ResourceScriptSource(new ClassPathResource("lua/redis_rate_limiter.lua"))
        );
        this.rateLimiterScript.setResultType(List.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String key = config.getKeyPrefix() + ":" + resolveKey(exchange, config);
            List<String> keys = Collections.singletonList(key);

            long now = System.currentTimeMillis();
            List<String> args = Arrays.asList(
                    String.valueOf(config.getCapacity()),
                    String.valueOf(config.getReplenishRate()),
                    String.valueOf(now),
                    "1"
            );
            return redisTemplate.execute(rateLimiterScript, keys, args).flatMap(result -> {
                @SuppressWarnings("unchecked")
                List<Long> typedResult = (List<Long>) result; // 扁平化 Flux<List<Long>> 中的 List<Long>

                boolean allowed = typedResult.get(0) == 1L;
                if (allowed) {
                    return chain.filter(exchange);
                } else {
                    exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                    return exchange.getResponse().setComplete();  // 结束请求，返回 Mono<Void>
                }
            }).then();
        };
    }



    private String resolveKey(ServerWebExchange exchange, Config config) {
        if ("ip".equalsIgnoreCase(config.getType())) {
            return exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        } else {
            return exchange.getRequest().getPath().value();
        }
    }

    @Data
    public static class Config {
        private String keyPrefix = "gateway:rate";
        private int capacity = 5;
        private int replenishRate = 5; // 每秒令牌恢复速率
        private String type = "ip"; // 可选值：ip、path、userId 等
    }
}
