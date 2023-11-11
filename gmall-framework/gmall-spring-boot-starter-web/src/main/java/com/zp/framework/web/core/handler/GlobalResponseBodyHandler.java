package com.zp.framework.web.core.handler;

import com.zp.framework.common.pojo.Result;
import com.zp.framework.web.util.WebFrameworkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Author : zhengpanone
 * Date : 2023/11/14 22:00
 * Version : v1.0.0
 * Description: 全局响应结果（ResponseBody）处理器
 * 不同于在网上看到的很多文章，会选择自动将 Controller 返回结果包上 {@link Result}，
 * 在 onemall 中，是 Controller 在返回时，主动自己包上 {@link Result}。
 * 原因是，GlobalResponseBodyHandler 本质上是 AOP，它不应该改变 Controller 返回的数据结构
 * <p>
 * 目前，GlobalResponseBodyHandler 的主要作用是，记录 Controller 的返回结果，
 * 方便 {@link com.zp.framework.apilog.core.filter.ApiAccessLogFilter} 记录访问日志
 * ResponseBodyAdvice 是对 Controller 返回的内容在 HttpMessageConverter 进行类型转换之前拦截，进行相应的处理操作后，再将结果返回给客户端
 */
@Component // 注入到spring容器进行拦截
@RestControllerAdvice(annotations = {RestController.class}) // 通过注解进行过滤哪些请求响应会被拦截，避免错误拦截
public class GlobalResponseBodyHandler<T> implements ResponseBodyAdvice<T> {
    private Logger log = LoggerFactory.getLogger(GlobalResponseBodyHandler.class);

    /**
     * 判断是否要交给 beforeBodyWrite 方法执行，ture：需要；false：不需要
     *
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    @SuppressWarnings("NullableProblems") // 避免 IDEA 警告
    public boolean supports(MethodParameter returnType, Class converterType) {
        /**
         * 我们可以选择哪些方法或者类进入beforeBodyWrite方法
         * 从returnType获取类名和方法名
         * 通过returnType.getMethod().getDeclaringClass.getName获取类名
         * converterType 表示当前请求使用的一个数据转换器，根据我们在controller指定返回类型决定，这里有个问题点待会会说
         */
        if (returnType.getMethod() == null) {
            return false;
        }
        log.info(returnType.getMethod().getDeclaringClass().getName());
        log.info(converterType.toString());
        // 只拦截返回结果为 CommonResult 类型
        return returnType.getMethod().getReturnType() == Result.class;

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
    @SuppressWarnings("NullableProblems") // 避免 IDEA 警告
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class
            selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info(selectedContentType.getType());
        /* ObjectMapper objectMapper = new ObjectMapper(); // jackson序列化
         *//**
         * body—请求即将返回给客户端的实体信息
         * body还可能存在出现异常的情况，需要进行处理
         *//*
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
        return Result.ok(body);*/

        // 记录 Controller 结果
        WebFrameworkUtils.setResult(((ServletServerHttpRequest) request).getServletRequest(), (Result<?>) body);
        return body;
    }

}
