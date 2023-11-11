package com.zp.gateway.util;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;


/**
 * Author : zhengpanone
 * Date : 2023/11/19 23:40
 * Version : v1.0.0
 * Description:
 * Web 工具类
 * <p>
 * copy from yudao-spring-boot-starter-web 的 WebFrameworkUtils 类
 */
@Slf4j
public class WebFrameworkUtils {

    private static final String HEADER_TENANT_ID = "tenant-id";

    private WebFrameworkUtils() {
    }

    /**
     * 将 Gateway 请求中的 header，设置到 HttpHeaders 中
     *
     * @param tenantId 租户编号
     * @param headers  WebClient请求
     */
    public static void setTenantIdHeader(Long tenantId, HttpHeaders headers) {
        if (tenantId == null) {
            return;
        }
        headers.set(HEADER_TENANT_ID, String.valueOf(tenantId));
    }

    public static Long getTenantId(ServerWebExchange exchange) {
        String tenantId = exchange.getRequest().getHeaders().getFirst(HEADER_TENANT_ID);
        return NumberUtil.isNumber(tenantId) ? Long.valueOf(tenantId) : null;
    }

    public static String getClientId(ServerWebExchange exchange, String... otherHeaderNames) {
        String[] headers = {"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        if (ArrayUtil.isNotEmpty(otherHeaderNames)) {
            headers = ArrayUtil.addAll(headers, otherHeaderNames);
        }
        // 方式一，通过 header 获取
        String ip;
        for (String header : headers) {
            ip = exchange.getRequest().getHeaders().getFirst(header);
            if (!NetUtil.isUnknown(ip)) {
                return NetUtil.getMultistageReverseProxyIp(ip);
            }
        }
        // 方式二，通过remoteAddress获取
        if (exchange.getRequest().getRemoteAddress() == null) {
            return null;
        }
        ip = exchange.getRequest().getRemoteAddress().getHostString();
        return NetUtil.getMultistageReverseProxyIp(ip);
    }

    /**
     * 获得请求匹配的Route路由
     *
     * @param exchange 请求
     * @return 路由
     */
    public static Route getGatewayRoute(ServerWebExchange exchange) {
        return exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
    }
}
