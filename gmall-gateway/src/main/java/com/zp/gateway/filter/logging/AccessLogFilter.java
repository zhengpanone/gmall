package com.zp.gateway.filter.logging;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.zp.framework.common.utils.json.JsonUtils;
import com.zp.gateway.util.SecurityFrameworkUtils;
import com.zp.gateway.util.WebFrameworkUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.CodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_MS_FORMATTER;

/**
 * Author : zhengpanone
 * Date : 2023/11/19 13:53
 * Version : v1.0.0
 * Description: 网关的访问日志过滤器
 * 从功能上，它类似 yudao-spring-boot-starter-web 的 ApiAccessLogFilter 过滤器
 */
@Slf4j
@Component
public class AccessLogFilter implements GlobalFilter, Ordered {
    @Resource
    private CodecConfigurer codecConfigurer;

    /**
     * 打印日志
     *
     * @param gatewayLog 网关日志
     */
    private void writeAccessLog(AccessLog gatewayLog) {
        // 方式一： 打印Logger后，通过ELK进行收集
        // log.info("[writeAccessLog][日志内容：{}]", JsonUtils.toJsonString(gatewayLog));
        // 方式二：调用远程服务，记录到数据库中
        // TODO ：暂未实现
        // 方式三：打印到控制台，方便排查错误
        HashMap<Object, Object> values = MapUtil.newHashMap(15, true);// 手工拼接，保证排序；15 保证不用扩容
        values.put("userId", gatewayLog.getDuration());
        values.put("userType", gatewayLog.getUserType());
        values.put("routeId", gatewayLog.getRoute() != null ? gatewayLog.getRoute().getId() : null);
        values.put("schema", gatewayLog.getSchema());
        values.put("requestUrl", gatewayLog.getRequestUrl());
        values.put("queryParams", gatewayLog.getQueryParams().toSingleValueMap());
        values.put("requestBody", JsonUtils.isJSON(gatewayLog.getRequestBody()) ? // 保证 body 的展示好看
                JSONUtil.parse(gatewayLog.getRequestBody()) : gatewayLog.getRequestBody());
        values.put("requestHeaders", JsonUtils.toJsonString(gatewayLog.getRequestHeaders().toSingleValueMap()));
        values.put("userIp", gatewayLog.getUserIp());
        values.put("responseBody", JsonUtils.isJSON(gatewayLog.getResponseBody()) ? // 保证 body 的展示好看
                JSONUtil.parse(gatewayLog.getResponseBody()) : gatewayLog.getResponseBody());
        values.put("responseHeaders", gatewayLog.getResponseHeaders() != null ?
                JsonUtils.toJsonString(gatewayLog.getResponseHeaders().toSingleValueMap()) : null);
        values.put("httpStatus", gatewayLog.getHttpStatus());
        values.put("startTime", LocalDateTimeUtil.format(gatewayLog.getStartTime(), NORM_DATETIME_MS_FORMATTER));
        values.put("endTime", LocalDateTimeUtil.format(gatewayLog.getEndTime(), NORM_DATETIME_MS_FORMATTER));
        values.put("duration", gatewayLog.getDuration() != null ? gatewayLog.getDuration() + " ms" : null);
        log.info("[writeAccessLog][网关日志：{}]", JsonUtils.toJsonPrettyString(values));


    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 将 Request 中可以直接获取到的参数，设置到网关日志
        ServerHttpRequest request = exchange.getRequest();
        // TODO traceId
        AccessLog gatewayLog = new AccessLog();
        gatewayLog.setRoute(WebFrameworkUtils.getGatewayRoute(exchange));
        gatewayLog.setSchema(request.getURI().getScheme());
        gatewayLog.setRequestMethod(request.getMethod().name());
        gatewayLog.setRequestUrl(request.getURI().getRawPath());
        gatewayLog.setQueryParams(request.getQueryParams());
        gatewayLog.setRequestHeaders(request.getHeaders());
        gatewayLog.setStartTime(LocalDateTime.now());
        gatewayLog.setUserIp(WebFrameworkUtils.getClientId(exchange));
        // 继续 filter 过滤
        MediaType mediaType = request.getHeaders().getContentType();
        if (MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(mediaType)
                || MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) {// 适合 JSON 和 Form 提交的请求
            return filterWithRequestBody(exchange, chain, gatewayLog);
        }
        return filterWithoutRequestBody(exchange, chain, gatewayLog);
    }

    private Mono<Void> filterWithoutRequestBody(ServerWebExchange exchange, GatewayFilterChain chain, AccessLog accessLog) {
        // 包装Response,用于记录Response Body
        ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, accessLog);
        return chain.filter(exchange.mutate().response(decoratedResponse).build())
                .then(Mono.fromRunnable(() -> writeAccessLog(accessLog))); // 打印日志
    }

    /**
     * 参考 {@link ModifyRequestBodyGatewayFilterFactory} 实现
     * <p>
     * 差别主要在于使用 modifiedBody 来读取 Request Body 数据
     */
    private Mono<Void> filterWithRequestBody(ServerWebExchange exchange, GatewayFilterChain chain, AccessLog accessLog) {
        // 设置Request Body 读取时，设置到网关日志
        // 此处 codeConfigurer.getReaders()的目的，是解决spring.codec.max-in-memory-size不生效
        ServerRequest serverRequest = ServerRequest.create(exchange, codecConfigurer.getReaders());
        Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).flatMap(body -> {
            accessLog.setRequestBody(body);
            return Mono.just(body);
        });
        // 创建BodyInserter对象
        BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
        // 创建CacheBodyOutputMessage对象
        HttpHeaders headers = new HttpHeaders();
        // the new content type will be computed by bodyInserter
        // and then set in the request decorator
        headers.remove(HttpHeaders.CONTENT_LENGTH); // 移除
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        // 通过 BodyInserter 将 Request Body 写入到 CachedBodyOutputMessage 中
        return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
            // 包装Request,用于缓存Request Body
            ServerHttpRequestDecorator decoratedRequest = requestDecorate(exchange, headers, outputMessage);
            // 包装 Response，用于记录 Response Body
            ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, accessLog);
            // 记录普通的
            return chain.filter(exchange.mutate().request(decoratedRequest).response(decoratedResponse).build())
                    .then(Mono.fromRunnable(() -> writeAccessLog(accessLog))); // 打印日志
        }));
    }

    /**
     * 记录响应日志
     * 通过 DataBufferFactory 解决响应体分段传输问题。
     *
     * @param exchange
     * @param accessLog
     * @return
     */
    private ServerHttpResponseDecorator recordResponseLog(ServerWebExchange exchange, AccessLog accessLog) {
        ServerHttpResponse response = exchange.getResponse();
        return new ServerHttpResponseDecorator(response) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    DataBufferFactory dataBufferFactory = response.bufferFactory();
                    // 计算执行时间
                    accessLog.setEndTime(LocalDateTime.now());
                    accessLog.setDuration((int) LocalDateTimeUtil.between(accessLog.getStartTime(), accessLog.getEndTime()).toMillis());
                    // 设置其他字段
                    accessLog.setUserId(SecurityFrameworkUtils.getLoginUserId(exchange));
                    accessLog.setUserType(SecurityFrameworkUtils.getLoginUserType(exchange));
                    accessLog.setResponseHeaders(response.getHeaders());
                    accessLog.setHttpStatus((HttpStatus) response.getStatusCode());
                    // 获取响应类型、如果是json就打印
                    String originalResponseContentType = exchange.getAttribute(ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
                    if (StringUtils.isNotBlank(originalResponseContentType)
                            && originalResponseContentType.contains("application/json")) {
                        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                        return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                            // 设置response body到网关日志
                            byte[] content = readContent(dataBuffers);
                            String responseResult = new String(content, StandardCharsets.UTF_8);
                            accessLog.setResponseBody(responseResult);
                            // 响应
                            return dataBufferFactory.wrap(content);
                        }));
                    }
                }
                // if body is not a flux. never got there.
                return super.writeWith(body);
            }
        };
    }

    // ========== 参考 ModifyResponseBodyGatewayFilterFactory 中的方法 ==========
    private byte[] readContent(List<? extends DataBuffer> dataBuffers) {
        // 合并多个流集合，解决返回体分段传输
        DefaultDataBufferFactory defaultDataBufferFactory = new DefaultDataBufferFactory();
        DefaultDataBuffer join = defaultDataBufferFactory.join(dataBuffers);
        byte[] content = new byte[join.readableByteCount()];
        join.read(content);
        // 释放掉内存
        DataBufferUtils.release(join);
        return content;
    }

    private ServerHttpRequestDecorator requestDecorate(ServerWebExchange exchange, HttpHeaders headers, CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                if (contentLength > 0) {
                    httpHeaders.setContentLength(contentLength);
                } else {
                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                }
                return httpHeaders;
            }

            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };

    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
