package com.zp.gmall.module.system.framework.security.config;

import com.zp.gmall.framework.security.config.AuthorizeRequestsCustomizer;
import com.zp.gmall.module.system.enums.ApiConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import java.util.List;

/**
 * system 模块的安全扩展配置。
 *
 * <p>框架层会收集所有 {@link AuthorizeRequestsCustomizer} Bean，并在构建
 * SecurityFilterChain 时统一应用。这个类只维护 system 模块自己的 URL 授权规则，
 * 避免把模块级白名单散落到通用安全框架里。</p>
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-13
 */
@Configuration(proxyBeanMethods = false, value = "systemSecurityConfiguration")
public class SecurityConfiguration {

    /**
     * 提供 system 模块的 URL 授权自定义器。
     *
     * <p>当前主要通过 {@link AuthorizeRequestsCustomizer#permitAllUrls()} 声明免登录 URL。
     * 如果后续需要增加「需要登录但有特殊权限表达式」的规则，再放到 customize 方法里。</p>
     *
     * @return system 模块的 Spring Security 授权扩展点
     */
    @Bean
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {
            /**
             * system 模块允许匿名访问的 URL 白名单。
             *
             * <p>这些路径会被框架统一合并到两处：</p>
             * <ul>
             *     <li>Spring Security 的 requestMatchers(...).permitAll()</li>
             *     <li>TokenAuthenticationFilter 的 permitAllUrls，用于跳过 token 解析</li>
             * </ul>
             *
             * <p>因此这里适合放「完全不需要登录/Token」的路径。匹配时使用的是
             * request.getServletPath()，通常不需要带服务 context-path；后台管理接口建议使用
             * buildAdminApi("/xxx") 拼接统一的 admin-api 前缀。</p>
             *
             * @return 免登录 URL 列表，支持 Ant 风格通配符
             */
            @Override
            public List<String> permitAllUrls() {
                return List.of(
//                        // 如果需要放开具体后台管理接口，可以改用 buildAdminApi 拼接后台 API 前缀。
                        buildAdminApi("/tenant/anonymous/**"),
                        buildAdminApi("/config/anonymous/**"),
                        // OpenAPI / Swagger UI 文档资源。
                        "/v3/api-docs/**",
                        "/webjars/**",
                        "/swagger-ui/**",
                        // 监控和运维相关端点，常用于开发环境或被外部监控系统访问。
                        "/druid/**",
                        "/actuator",
                        "/actuator/**",
                        // system 模块提供给内部服务调用的 RPC API 前缀。
                        ApiConstants.PREFIX + "/**"
                );
            }

            /**
             * system 模块的精细授权规则。
             *
             * <p>这里的规则会在全局 permitAll 规则之后、anyRequest().authenticated() 兜底规则之前执行。
             * 适合放「必须登录，并且还要校验权限/角色/表达式」的接口，例如 hasAuthority、hasRole、access 等。</p>
             *
             * <p>注意：完全公开、无需 token 的接口不要只在这里写 requestMatchers(...).permitAll()，
             * 应优先放到 {@link #permitAllUrls()}，这样 Spring Security 和 TokenAuthenticationFilter
             * 都能同时放行。</p>
             *
             * @param registry Spring Security URL 授权规则注册器
             */
            @Override
            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
                // 示例一：按权限标识控制接口访问，用户必须登录且拥有对应 authority。
//                registry
//                        .requestMatchers(buildAdminApi("/user/page"))
//                        .hasAuthority("system:user:query")
//                        .requestMatchers(buildAdminApi("/user/create"))
//                        .hasAuthority("system:user:create")
//                        .requestMatchers(buildAdminApi("/user/update"))
//                        .hasAuthority("system:user:update")
//                        .requestMatchers(buildAdminApi("/user/delete"))
//                        .hasAuthority("system:user:delete");

                // 示例二：按 HTTP 请求方法控制同一类接口的不同操作权限。
                // 如果启用该示例，需要引入 org.springframework.http.HttpMethod。
//                registry
//                        .requestMatchers(HttpMethod.GET, buildAdminApi("/config/**"))
//                        .hasAuthority("system:config:query")
//                        .requestMatchers(HttpMethod.POST, buildAdminApi("/config/**"))
//                        .hasAuthority("system:config:create");
            }
        };
    }
}
