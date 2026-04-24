package com.zp.gmall.module.ai.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author : zhengpanone
 * Date : 2026/4/24 20:09
 * Version : v1.0.0
 * Description:
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                        .title("AI服务")
                        .description("AI服务")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("zhengpanone")
                                .email("zhengpanone@hotmail.com")
                                .url("https://github.com/zhengpanone")
                        )
                        .license(new License().name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                        .termsOfService("https://github.com/zhengpanone/gmall")

                )
                // 新增：配置Server URL，指向网关地址
                .servers(List.of(
                        new Server().url("/admin-api/ai").description("通过网关访问")
                ));
    }
}
