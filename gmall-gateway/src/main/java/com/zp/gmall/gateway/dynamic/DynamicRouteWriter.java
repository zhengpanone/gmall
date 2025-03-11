//package com.zp.gmall.gateway.dynamic;
//
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * Author : zhengpanone
// * Date : 2025/4/9 11:29
// * Version : v1.0.0
// * Description: 动态路由写入器 动态修改路由
// */
//@Component
//public class DynamicRouteWriter implements RouteDefinitionWriter {
//
//    private final Map<String, RouteDefinition> routes = new ConcurrentHashMap<>();
//
//    @Override
//    public Mono<Void> save(Mono<RouteDefinition> route) {
//        return route.doOnNext(r -> routes.put(r.getId(), r)).then();
//    }
//
//    @Override
//    public Mono<Void> delete(Mono<String> routeId) {
//        return routeId.doOnNext(routes::remove).then();
//    }
//}
