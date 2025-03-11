//package com.zp.gmall.gateway.listener;
//
//import com.zp.gmall.gateway.event.RefreshRoutesEvent;
//import com.zp.gmall.gateway.service.GatewayRouteService;
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
///**
// * Author : zhengpanone
// * Date : 2025/4/9 11:26
// * Version : v1.0.0
// * Description: 监听Bus事件，刷新路由
// */
//@Slf4j
//@Component
//public class CustomRouteRefreshListener {
//    @Resource
//    private ApplicationEventPublisher publisher;
//    @Resource
//    private GatewayRouteService routeService;
//
//    @EventListener
//    public void routeRefreshEvent(RefreshRoutesEvent event) {
//        log.info("收到路由刷新事件，开始更新缓存和内存...");
//        routeService.refreshCache();
//        publisher.publishEvent(new RefreshRoutesEvent(this, "gateway-server"));
//
//    }
//}
