package com.zp.gmall.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zp.gmall.gateway.mapper.GatewayRouteMapper;
import com.zp.gmall.gateway.model.GatewayRoute;
import com.zp.gmall.gateway.service.GatewayRouteService;
import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/4/9 11:49
 * Version : v1.0.0
 * Description:
 */
@Service
public class GatewayRouteServiceImpl extends ServiceImpl<GatewayRouteMapper, GatewayRoute> implements GatewayRouteService {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void refreshCache() {

    }

    @Override
    public List<RouteDefinition> loadRouteDefinitions() {
        List<GatewayRoute> routes = baseMapper.selectList(null);
        List<RouteDefinition> routeDefinitions = routes.stream().filter(GatewayRoute::getEnabled).map(this::convertToRouteDefinition).toList();
        return routeDefinitions;
    }

    private RouteDefinition convertToRouteDefinition(GatewayRoute route) {
        RouteDefinition definition = new RouteDefinition();
        definition.setId(route.getRouteId());
        definition.setUri(URI.create(route.getUri()));

        try {

            List<PredicateDefinition> predicates = objectMapper.readValue(route.getPredicates(), new TypeReference<>() {
            });
            definition.setPredicates(predicates);
            List<FilterDefinition> filters = objectMapper.readValue(route.getFilters(), new TypeReference<List<FilterDefinition>>() {
            });
            definition.setFilters(filters);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse predicates/filters JSON", e);
        }

        return definition;
    }
}
