package com.zp.gmall.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.gateway.model.GatewayRoute;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/4/9 11:28
 * Version : v1.0.0
 * Description: 缓存+数据库交互
 */
public interface GatewayRouteService extends IService<GatewayRoute> {

    void refreshCache();

    List<RouteDefinition> loadRouteDefinitions();
}
