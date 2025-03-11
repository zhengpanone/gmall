package com.zp.gmall.gateway.filter;

import com.zp.gmall.gateway.mapper.GatewayRateLimitMapper;
import com.zp.gmall.gateway.model.GatewayRateLimit;
import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/4/9 13:03
 * Version : v1.0.0
 * Description: 局部过滤器
 * 继承 AbstractGatewayFilterFactory 让 Spring Cloud Gateway 识别该类作为局部过滤器。
 */
@Component
public class RateLimitGatewayFilter extends AbstractGatewayFilterFactory<RateLimitGatewayFilter.Config> {

    private final RedisTemplate<String, Object> redisTemplate;

    private final GatewayRateLimitMapper rateLimitMapper;


    public RateLimitGatewayFilter(RedisTemplate<String, Object> redisTemplate, GatewayRateLimitMapper rateLimitMapper) {
        super(Config.class);
        this.redisTemplate = redisTemplate;
        this.rateLimitMapper = rateLimitMapper;
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String key = buildKey(config, exchange);
            boolean allowed = isAllowed(key, config);
            if (allowed) {
                return chain.filter(exchange);
            } else {
                exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                return exchange.getResponse().setComplete();
            }

        };
    }

    private String buildKey(Config config, ServerWebExchange exchange) {
        String ip = exchange.getRequest().getHeaders().getFirst("x-forwarded-for");
        if (ip == null) {
            ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        }
        return "rate_limit:" + ip + ":" + config.getKey();
    }

    private boolean isAllowed(String key, Config config) {
        GatewayRateLimit rateLimit = rateLimitMapper.selectById(config.getRouteId());
        String luaScript = getLuaScript();
        List<String> keys = Collections.singletonList(key);
        List<String> args = new ArrayList<>();
        if (rateLimit == null) {
            args = Arrays.asList(
                    String.valueOf(System.currentTimeMillis()),
                    String.valueOf(config.getWindow()),
                    String.valueOf(config.getLimit())
            );
        } else {
            args = Arrays.asList(
                    String.valueOf(System.currentTimeMillis()),
                    String.valueOf(rateLimit.getWindowSize()),
                    String.valueOf(rateLimit.getMaxRequests())
            );
        }

        Long result = redisTemplate.execute(
                new DefaultRedisScript<>(luaScript, Long.class),
                keys, args.toArray()
        );
        return result == 1;
    }


    private String getLuaScript() {
        return """
                local key = KEYS[1]
                local now = tonumber(ARGV[1])
                local window = tonumber(ARGV[2])
                local limit = tonumber(ARGV[3])
                redis.call("ZREMRANGEBYSCORE", key, 0, now - window)
                local count = redis.call("ZCARD", key)
                if count < limit then
                    redis.call("ZADD", key, now, now)
                    redis.call("PEXPIRE", key, window)
                    return 1
                else
                    return 0
                end
                """;
    }

    // 过滤器的配置类
    @Data
    public static class Config {

        /**
         * 路由ID，用于在数据库中查询限流配置
         */
        private String routeId;

        /**
         * 限流窗口时间，单位：毫秒
         */
        private long window = 1000;

        /**
         * 窗口时间内允许的请求数
         */
        private long limit = 5;

        /**
         * 自定义键（接口名/路径名等）
         */
        private String key = "default";
    }
}


//配置 application.yml 使用自定义过滤器
//spring:
//   cloud:
//     gateway:
//       routes:
//         - id: test-api
//           uri: http://localhost:8081
//           predicates:
//             - Path=/api/test/**
//           filters:
//             - name: RateLimitGatewayFilter # 添加自定义限流过滤器
//               args:
//                 window: 1000       # 时间窗口（毫秒）
//                 limit: 3           # 最大请求数
//                 key: test-api      # 自定义 key
//


//spring:
//  cloud:
//    gateway:
//      routes:
//        - id: user-service
//          uri: lb://user-service
//          predicates:
//            - Path=/user/**
//          filters:
//            - StripPrefix=1
//            - name: RateLimitGatewayFilter  # 添加自定义限流过滤器
//              args:
//                 window: 1000       # 时间窗口（毫秒）
//                 limit: 3           # 最大请求数
//                 key: test-api      # 自定义 key

