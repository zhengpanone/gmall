package com.zp.gmall.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.springdoc.core.utils.Constants.DEFAULT_API_DOCS_URL;

/**
 * Author : zhengpanone
 * Date : 2025/4/8 16:18
 * Version : v1.0.0
 * Description:  SpringDoc配置类，实现InitializingBean接口 swagger 3.0 展示
 */
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(value = "springdoc.api-docs.enabled", matchIfMissing = true)
public class SpringDocConfiguration {


    @Bean
    @Lazy(false)
    public Set<SwaggerUrl> apis(RouteDefinitionLocator locator, SwaggerUiConfigProperties swaggerUiConfigProperties) {
        Set<SwaggerUrl> urls = new LinkedHashSet<>();

        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        if (definitions == null) return urls;

        // 提取符合规则的路由：如 admin-api/system 和 app-api/system
        for (RouteDefinition definition : definitions) {
            String routeId = definition.getId();

            // 只处理带 api 的路由（你也可以用其他规则）
            if (!routeId.endsWith("-api")) continue;

            String name = routeId; // 你可以自定义显示名

            // 提取 Path predicate
            String path = definition.getPredicates().stream()
                    .filter(predicate -> "Path".equalsIgnoreCase(predicate.getName()))
                    .flatMap(predicate -> predicate.getArgs().values().stream())
                    .findFirst()
                    .orElse(null);

            if (path == null) continue;

            // 根据 path 构造接口文档地址
            // 例如：/admin-api/system/** → /admin-api/system/v3/api-docs
            String docPath = path.replace("/**", DEFAULT_API_DOCS_URL);

            SwaggerUrl swaggerUrl = new SwaggerUrl(name, docPath, null);
            urls.add(swaggerUrl);
        }

        swaggerUiConfigProperties.setUrls(urls);
        return urls;
    }

}



