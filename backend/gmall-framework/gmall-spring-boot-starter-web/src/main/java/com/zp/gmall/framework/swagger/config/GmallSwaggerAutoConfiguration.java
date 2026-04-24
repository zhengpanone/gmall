package com.zp.gmall.framework.swagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.OpenApiBuilderCustomizer;
import org.springdoc.core.customizers.ServerBaseUrlCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.providers.JavadocProvider;
import org.springdoc.core.service.OpenAPIService;
import org.springdoc.core.service.SecurityService;
import org.springdoc.core.utils.PropertyResolverUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.zp.gmall.framework.common.constant.GlobalConstant.HEADER_TENANT_ID;


/**
 * Author : zhengpanone
 * Date : 2025/3/26 21:18
 * Version : v1.0.0
 * Description:
 * Swagger 自动配置类，基于 OpenAPI + Springdoc 实现。
 * 友情提示：
 * 1. Springdoc 文档地址：<a href="https://github.com/springdoc/springdoc-openapi">仓库</a>
 * 2. Swagger 规范，于 2015 更名为 OpenAPI 规范，本质是一个东西
 */
@AutoConfiguration
@ConditionalOnClass({OpenAPI.class})
// 启用 SwaggerProperties 配置类
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(prefix = "springdoc.api-docs", name = "enabled", havingValue = "true", matchIfMissing = true)
public class GmallSwaggerAutoConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    // ========== 全局 OpenAPI 配置 ==========
    @Bean
    public OpenAPI createOpenAPI(SwaggerProperties swaggerProperties) {
        Map<String, SecurityScheme> securitySchemeMap = buildSecuritySchemes();
        OpenAPI openAPI = new OpenAPI()
                .info(buildInfo(swaggerProperties))
                .components(new Components().securitySchemes(securitySchemeMap))
                .addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION));
        securitySchemeMap.keySet().forEach(key -> openAPI.addSecurityItem(new SecurityRequirement().addList(key)));
        return openAPI;
    }

    /**
     * 安全模式，这里配置通过请求头 Authorization 传递 token 参数
     */
    private Map<String, SecurityScheme> buildSecuritySchemes() {
        Map<String, SecurityScheme> securitySchemes = new HashMap<>();
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .name(HttpHeaders.AUTHORIZATION)
                .in(SecurityScheme.In.HEADER);
        securitySchemes.put(HttpHeaders.AUTHORIZATION, securityScheme);
        return securitySchemes;
    }

    /**
     * API 摘要信息
     */
    private Info buildInfo(SwaggerProperties properties) {
        return new Info()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .version(properties.getVersion())
                .contact(new Contact().name(properties.getAuthor()).url(properties.getUrl()).email(properties.getEmail()))
                .license(new License().name(properties.getLicense()).url(properties.getLicenseUrl()));
    }

    /**
     * 自定义 OpenAPI 处理器
     */
    @Bean
    // 目的：以我们创建的 OpenAPIService Bean 为主，避免一键改包后，启动报错！
    @Primary
    public OpenAPIService openApiBuilder(Optional<OpenAPI> openAPI,
                                         SecurityService securityService,
                                         SpringDocConfigProperties springDocConfigProperties,
                                         PropertyResolverUtils propertyResolverUtils,
                                         Optional<List<OpenApiBuilderCustomizer>> openApiBuilderCustomizers,
                                         Optional<List<ServerBaseUrlCustomizer>> serverBaseUrlCustomizers,
                                         Optional<JavadocProvider> javadocProvider) {
        return new OpenAPIService(openAPI, securityService, springDocConfigProperties,
                propertyResolverUtils, openApiBuilderCustomizers, serverBaseUrlCustomizers, javadocProvider);
    }

    // ========== 分组 OpenAPI 配置 ==========

    /**
     * 所有模块的 API 分组
     */
    @Bean
    public GroupedOpenApi allGroupedOpenApi(OpenAPI openAPI) {
        // 使用 application.name 作为分组名称,如果没有则默认all
        String groupName = StringUtils.isNotBlank(applicationName) ? applicationName : "all";
        return buildGroupedOpenApi(groupName, "");
    }

    public static GroupedOpenApi buildGroupedOpenApi(String group, String path) {
        return GroupedOpenApi.builder().group(group).pathsToMatch("/admin-api/" + path + "/**", "/app-api/" + path + "/**")
                .addOperationCustomizer((operation, handlerMethod) ->
                        operation.addParametersItem(buildTenanntHeaderParameter())
                                .addParametersItem(buildSecurityHeaderParameter()))
                .build();
    }

    /**
     * 构建 Tenant 租户编号请求头参数
     *
     * @return 多租户参数
     */
    private static Parameter buildTenanntHeaderParameter() {
        return new Parameter()
                .name(HEADER_TENANT_ID)
                .description("租户编号")
                .in(String.valueOf(SecurityScheme.In.HEADER))
                .schema(new StringSchema()._default("0").name(HEADER_TENANT_ID)
                        .description("租户编号"));
    }

    /**
     * 构建 Authorization 认证请求头参数
     * <p>
     * 解决 Knife4j <a href="https://gitee.com/xiaoym/knife4j/issues/I69QBU">Authorize 未生效，请求header里未包含参数</a>
     *
     * @return 认证参数
     */
    private static Parameter buildSecurityHeaderParameter() {
        return new Parameter().name(HttpHeaders.AUTHORIZATION)
                .description("认证 Token")
                .in(String.valueOf(SecurityScheme.In.HEADER))
                .schema(new StringSchema()._default("Bearer test1")
                        .name(HEADER_TENANT_ID).description("认证 Token"));
    }
}
