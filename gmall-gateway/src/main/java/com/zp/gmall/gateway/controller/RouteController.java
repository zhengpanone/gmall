package com.zp.gmall.gateway.controller;

//import com.zp.gmall.gateway.event.RefreshRoutesEvent;
import com.zp.gmall.gateway.service.GatewayRouteService;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : zhengpanone
 * Date : 2025/4/9 11:29
 * Version : v1.0.0
 * Description: 管理端接口
 */
@RestController
@RequestMapping("/routes")
public class RouteController {
    @Resource
    private GatewayRouteService routeService;
    @Resource
    private ApplicationEventPublisher publisher;

    @PostMapping("/refresh")
    public void refreshRoutes() {
        routeService.refreshCache();
        //publisher.publishEvent(new RefreshRoutesEvent(this,"gateway-server"));
    }
}
