package com.zp.gmall.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Author : zhengpanone
 * Date : 2025/4/10 17:52
 * Version : v1.0.0
 * Description:
 */
//@Configuration
public class JwtAuthFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (token == null || !validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            // 直接终止请求
            return exchange.getResponse().setComplete();
        }
        // 认证通过，继续执行下一个过滤器
        return chain.filter(exchange);
    }

    private boolean validateToken(String token) {
        // TODO
        return true;
    }

    @Override
    public int getOrder() {
        return -10;
    }
}
