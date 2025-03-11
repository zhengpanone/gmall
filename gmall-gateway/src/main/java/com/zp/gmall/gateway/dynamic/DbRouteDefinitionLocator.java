//package com.zp.gmall.gateway.dynamic;
//
//import com.zp.gmall.gateway.service.GatewayRouteService;
//import jakarta.annotation.Resource;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Flux;
//
//import java.util.List;
//
///**
// * Author : zhengpanone
// * Date : 2025/4/9 11:28
// * Version : v1.0.0
// * Description: 路由加载器 从DB动态加载路由
// */
//@Component
//public class DbRouteDefinitionLocator implements RouteDefinitionLocator {
//
//    @Resource
//    private GatewayRouteService routeService;
//
//    @Override
//    public Flux<RouteDefinition> getRouteDefinitions() {
//        // 从缓存/DB获取
//        List<RouteDefinition> list = routeService.loadRouteDefinitions();
//        return Flux.fromIterable(list);
//    }
//}
