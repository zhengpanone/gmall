package com.zp.framework.web.core.handler;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.zp.framework.apilog.core.service.ApiErrorLog;
import com.zp.framework.apilog.core.service.ApiErrorLogFrameworkService;
import com.zp.framework.common.enums.ResultEnum;
import com.zp.framework.common.exception.ServiceException;
import com.zp.framework.common.pojo.Result;
import com.zp.framework.common.utils.json.JsonUtils;
import com.zp.framework.common.utils.monitor.TracerUtils;
import com.zp.framework.common.utils.servlet.ServletUtils;
import com.zp.framework.web.util.WebFrameworkUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import static com.zp.framework.common.exception.enums.GlobalErrorCodeConstants.*;



/**
 * 全局异常处理,，将 Exception 翻译成 Result + 对应的异常编号
 */
@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final String applicationName;
    private final ApiErrorLogFrameworkService apiErrorLogFrameworkService;

    /**
     * 处理所有异常，主要是提供给 Filter 使用
     * 因为 Filter 不走 SpringMVC 的流程，但是我们又需要兜底处理异常，所以这里提供一个全量的异常处理过程，保持逻辑统一。
     *
     * @param request 请求
     * @param ex      异常
     * @return 通用返回
     */
    public Result<?> allExceptionHandler(HttpServletRequest request, Throwable ex) {
        if (ex instanceof MissingServletRequestParameterException) {
            return missingServletRequestParameterExceptionHandler((MissingServletRequestParameterException) ex);
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return methodArgumentTypeMismatchExceptionHandler((MethodArgumentTypeMismatchException) ex);
        }
        if (ex instanceof MethodArgumentNotValidException) {
            return methodArgumentNotValidExceptionExceptionHandler((MethodArgumentNotValidException) ex);
        }
        if (ex instanceof BindException) {
            return bindExceptionHandler((BindException) ex);
        }
        if (ex instanceof ConstraintViolationException) {
            return constraintViolationExceptionHandler((ConstraintViolationException) ex);
        }
        if (ex instanceof ValidationException) {
            return validationException((ValidationException) ex);
        }
        if (ex instanceof NoHandlerFoundException) {
            return noHandlerFoundExceptionHandler((NoHandlerFoundException) ex);
        }
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return httpRequestMethodNotSupportedExceptionHandler((HttpRequestMethodNotSupportedException) ex);
        }
        if (ex instanceof ServiceException) {
            return serviceExceptionHandler((ServiceException) ex);
        }
        if (ex instanceof AccessDeniedException) {
            return accessDeniedExceptionHandler(request, (AccessDeniedException) ex);
        }
        return defaultExceptionHandler(request, ex);
    }

    /**
     * 处理 SpringMVC 请求参数缺失
     * <p>
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        log.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return Result.failed(BAD_REQUEST.code(), String.format("请求参数缺失:%s", ex.getParameterName()));
    }

    /**
     * 处理 SpringMVC 请求参数类型错误
     * <p>
     * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        log.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return Result.failed(BAD_REQUEST.code(), String.format("请求参数类型错误:%s", ex.getMessage()));
    }

    /**
     * 处理 SpringMVC 参数校验不正确
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException ex) {
        log.warn("[methodArgumentNotValidExceptionExceptionHandler]", ex);
        FieldError fieldError = ex.getBindingResult().getFieldError();
        assert fieldError != null; // 断言，避免告警
        return Result.failed(BAD_REQUEST.code(), String.format("请求参数不正确:%s", fieldError.getDefaultMessage()));
    }

    /**
     * 处理 SpringMVC 参数绑定不正确，本质上也是通过 Validator 校验
     */
    @ExceptionHandler(BindException.class)
    public Result<?> bindExceptionHandler(BindException ex) {
        log.warn("[handleBindException]", ex);
        FieldError fieldfailed = ex.getFieldError();
        assert fieldfailed != null; // 断言，避免告警
        return Result.failed(BAD_REQUEST.code(), String.format("请求参数不正确:%s", fieldfailed.getDefaultMessage()));
    }

    /**
     * 处理 Validator 校验不通过产生的异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result<?> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.warn("[constraintViolationExceptionHandler]", ex);
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        return Result.failed(BAD_REQUEST.code(), String.format("请求参数不正确:%s", constraintViolation.getMessage()));
    }

    /**
     * 处理 Dubbo Consumer 本地参数校验时，抛出的 ValidationException 异常
     */
    @ExceptionHandler(value = ValidationException.class)
    public Result<?> validationException(ValidationException ex) {
        log.warn("[constraintViolationExceptionHandler]", ex);
        // 无法拼接明细的错误信息，因为 Dubbo Consumer 抛出 ValidationException 异常时，是直接的字符串信息，且人类不可读
        return Result.failed(BAD_REQUEST);
    }

    /**
     * 处理 SpringMVC 请求地址不存在
     * <p>
     * 注意，它需要设置如下两个配置项：
     * 1. spring.mvc.throw-exception-if-no-handler-found 为 true
     * 2. spring.mvc.static-path-pattern 为 /statics/**
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> noHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
        log.warn("[noHandlerFoundExceptionHandler]", ex);
        return Result.failed(NOT_FOUND.code(), String.format("请求地址不存在:%s", ex.getRequestURL()));
    }

    /**
     * 处理 SpringMVC 请求方法不正确
     * <p>
     * 例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.warn("[httpRequestMethodNotSupportedExceptionHandler]", ex);
        return Result.failed(METHOD_NOT_ALLOWED.code(), String.format("请求方法不正确:%s", ex.getMessage()));
    }

    /**
     * 处理 Resilience4j 限流抛出的异常
     */
    public Result<?> requestNotPermittedExceptionHandler(HttpServletRequest req, Throwable ex) {
        log.warn("[requestNotPermittedExceptionHandler][url({}) 访问过于频繁]", req.getRequestURL(), ex);
        return Result.failed(TOO_MANY_REQUESTS);
    }

    /**
     * 处理 Spring Security 权限不足的异常
     * <p>
     * 来源是，使用 @PreAuthorize 注解，AOP 进行权限拦截
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public Result<?> accessDeniedExceptionHandler(HttpServletRequest req, AccessDeniedException ex) {
        log.warn("[accessDeniedExceptionHandler][userId({}) 无法访问 url({})]", WebFrameworkUtils.getLoginUserId(req),
                req.getRequestURL(), ex);
        return Result.failed(FORBIDDEN);
    }

    /**
     * 处理业务异常 ServiceException
     * <p>
     * 例如说，商品库存不足，用户手机号已存在。
     */
    @ExceptionHandler(value = ServiceException.class)
    public Result<?> serviceExceptionHandler(ServiceException ex) {
        log.info("[serviceExceptionHandler]", ex);
        return Result.failed(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理系统异常，兜底处理所有的一切
     */
    @ExceptionHandler(value = Exception.class)
    public Result<?> defaultExceptionHandler(HttpServletRequest req, Throwable ex) {
        // 情况一：处理表不存在的异常
        Result<?> tableNotExistsResult = handleTableNotExists(ex);
        if (tableNotExistsResult != null) {
            return tableNotExistsResult;
        }

        // 情况二：部分特殊的库的处理
        if (Objects.equals("io.github.resilience4j.ratelimiter.RequestNotPermitted", ex.getClass().getName())) {
            return requestNotPermittedExceptionHandler(req, ex);
        }

        // 情况三：处理异常
        log.error("[defaultExceptionHandler]", ex);
        // 插入异常日志
        this.createExceptionLog(req, ex);
        // 返回 failed Result
        return Result.failed(INTERNAL_SERVER_ERROR.code(), INTERNAL_SERVER_ERROR.message());
    }

    private void createExceptionLog(HttpServletRequest req, Throwable e) {
        // 插入错误日志
        ApiErrorLog errorLog = new ApiErrorLog();
        try {
            // 初始化 failedLog
            initExceptionLog(errorLog, req, e);
            // 执行插入 failedLog
            apiErrorLogFrameworkService.createApiErrorLog(errorLog);
        } catch (Throwable th) {
            log.error("[createExceptionLog][url({}) log({}) 发生异常]", req.getRequestURI(), JsonUtils.toJsonString(errorLog), th);
        }
    }

    private void initExceptionLog(ApiErrorLog failedLog, HttpServletRequest request, Throwable e) {
        // 处理用户信息
        failedLog.setUserId(WebFrameworkUtils.getLoginUserId(request));
        failedLog.setUserType(WebFrameworkUtils.getLoginUserType(request));
        // 设置异常字段
        failedLog.setExceptionName(e.getClass().getName());
        failedLog.setExceptionMessage(ExceptionUtil.getMessage(e));
        failedLog.setExceptionRootCauseMessage(ExceptionUtil.getRootCauseMessage(e));
        failedLog.setExceptionStackTrace(ExceptionUtil.stacktraceToString(e));
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        Assert.notEmpty(stackTraceElements, "异常 stackTraceElements 不能为空");
        StackTraceElement stackTraceElement = stackTraceElements[0];
        failedLog.setExceptionClassName(stackTraceElement.getClassName());
        failedLog.setExceptionFileName(stackTraceElement.getFileName());
        failedLog.setExceptionMethodName(stackTraceElement.getMethodName());
        failedLog.setExceptionLineNumber(stackTraceElement.getLineNumber());
        // 设置其它字段
        failedLog.setTraceId(TracerUtils.getTraceId());
        failedLog.setApplicationName(applicationName);
        failedLog.setRequestUrl(request.getRequestURI());
        Map<String, Object> requestParams = MapUtil.<String, Object>builder()
                .put("query", ServletUtils.getParamMap(request))
                .put("body", ServletUtils.getBody(request)).build();
        failedLog.setRequestParams(JsonUtils.toJsonString(requestParams));
        failedLog.setRequestMethod(request.getMethod());
        failedLog.setUserAgent(ServletUtils.getUserAgent(request));
        failedLog.setUserIp(ServletUtils.getClientIP(request));
        failedLog.setExceptionTime(LocalDateTime.now());
    }

    /**
     * 处理 Table 不存在的异常情况
     *
     * @param ex 异常
     * @return 如果是 Table 不存在的异常，则返回对应的 Result
     */
    private Result<?> handleTableNotExists(Throwable ex) {
        String message = ExceptionUtil.getRootCauseMessage(ex);
        if (!message.contains("doesn't exist")) {
            return null;
        }
        // 1. 数据报表
        if (message.contains("report_")) {
            log.error("[报表模块 yudao-module-report - 表结构未导入][参考 https://doc.iocoder.cn/report/ 开启]");
            return Result.failed(NOT_IMPLEMENTED.code(),
                    "[报表模块 yudao-module-report - 表结构未导入][参考 https://doc.iocoder.cn/report/ 开启]");
        }
        // 2. 工作流
        if (message.contains("bpm_")) {
            log.error("[工作流模块 yudao-module-bpm - 表结构未导入][参考 https://doc.iocoder.cn/bpm/ 开启]");
            return Result.failed(NOT_IMPLEMENTED.code(),
                    "[工作流模块 yudao-module-bpm - 表结构未导入][参考 https://doc.iocoder.cn/bpm/ 开启]");
        }
        // 3. 微信公众号
        if (message.contains("mp_")) {
            log.error("[微信公众号 yudao-module-mp - 表结构未导入][参考 https://doc.iocoder.cn/mp/build/ 开启]");
            return Result.failed(NOT_IMPLEMENTED.code(),
                    "[微信公众号 yudao-module-mp - 表结构未导入][参考 https://doc.iocoder.cn/mp/build/ 开启]");
        }
        // 4. 商城系统
        if (StrUtil.containsAny(message, "product_", "promotion_", "trade_")) {
            log.error("[商城系统 yudao-module-mall - 已禁用][参考 https://doc.iocoder.cn/mall/build/ 开启]");
            return Result.failed(NOT_IMPLEMENTED.code(),
                    "[商城系统 yudao-module-mall - 已禁用][参考 https://doc.iocoder.cn/mall/build/ 开启]");
        }
        // 5. 支付平台
        if (message.contains("pay_")) {
            log.error("[支付模块 yudao-module-pay - 表结构未导入][参考 https://doc.iocoder.cn/pay/build/ 开启]");
            return Result.failed(NOT_IMPLEMENTED.code(),
                    "[支付模块 yudao-module-pay - 表结构未导入][参考 https://doc.iocoder.cn/pay/build/ 开启]");
        }
        return null;
    }
}
