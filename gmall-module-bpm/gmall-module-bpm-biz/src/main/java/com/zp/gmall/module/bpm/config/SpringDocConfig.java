package com.zp.gmall.module.bpm.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author : zhengpanone
 * Date : 2025/4/8 18:09
 * Version : v1.0.0
 * Description:
 */
@Configuration
public class SpringDocConfig {

    private static final String basePackage = "com.zp.gmall.module.bpm";
    // 请求头名称
    private static final String headerName = "token";

    @Bean
    public GroupedOpenApi buildGroupedOpenApi() {
        return GroupedOpenApi.builder().group("bpmGroup").addOperationCustomizer((operation, handlerMethod) -> {
            operation.addSecurityItem(new SecurityRequirement().addList(headerName));
            return operation;
        }).packagesToScan(basePackage).build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Components components = new Components();
        components.addSecuritySchemes(headerName, new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .scheme("basic")
                .name(headerName)
                .in(SecurityScheme.In.HEADER)
                .description("请求头"));
        return new OpenAPI().components(components).info(apiInfo());
    }


    private Info apiInfo() {
        Contact contact = new Contact();
        contact.setEmail("zhengpanone@hotmail.com");
        contact.setUrl("https://github.com/zhengpanone");
        contact.setName("zhengpanone");
        return new Info().title("bpm服务管理接口文档")
                .description("swagger接口文档")
                .version("1.0.0")
                .contact(contact);
    }
}

