package com.zp.common.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zp.common.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一包装处理
 * ResponseBodyAdvice 是对 Controller 返回的内容在 HttpMessageConverter 进行类型转换之前拦截，进行相应的处理操作后，再将结果返回给客户端
 */
@Component // 注入到spring容器进行拦截
@RestControllerAdvice(annotations = {RestController.class}) // 通过注解进行过滤哪些请求响应会被拦截，避免错误拦截
public class ResponseAdvice implements ResponseBodyAdvice {
    private Logger log = LoggerFactory.getLogger(ResponseAdvice.class);

    /**
     * 判断是否要交给 beforeBodyWrite 方法执行，ture：需要；false：不需要
     *
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        /**
         * 我们可以选择哪些方法或者类进入beforeBodyWrite方法
         * 从returnType获取类名和方法名
         * 通过returnType.getMethod().getDeclaringClass.getName获取类名
         * converterType 表示当前请求使用的一个数据转换器，根据我们在controller指定返回类型决定，这里有个问题点待会会说
         */
        log.info(returnType.getMethod().getDeclaringClass().getName());
        log.info(converterType.toString());
        return true;
    }

    /**
     * 对 response 进行具体的处理
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info(selectedContentType.getType());
        ObjectMapper objectMapper = new ObjectMapper(); // jackson序列化
        /**
         * body—请求即将返回给客户端的实体信息
         * body还可能存在出现异常的情况，需要进行处理
         */
        // 我们也可以根据selectConvertorType的类型进行判断
        if (body instanceof String) {
            try {
                return objectMapper.writeValueAsString(Result.ok(body));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if (body instanceof Result) {
            return body;
        }
        return Result.ok(body);
    }
}
