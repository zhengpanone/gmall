package com.zp.framework.web.core.handler;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.zp.framework.apilog.core.service.ApiErrorLog;
import com.zp.framework.apilog.core.service.ApiErrorLogFrameworkService;
import com.zp.framework.common.enums.ResultEnum;
import com.zp.framework.common.exception.ServiceException;
import com.zp.framework.common.pojo.Result;
import com.zp.framework.common.utils.json.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static com.zp.framework.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;
import static com.zp.framework.common.exception.enums.GlobalErrorCodeConstants.TOO_MANY_REQUESTS;


/**
 * 全局异常处理,，将 Exception 翻译成 CommonResult + 对应的异常编号
 */
@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final String applicationName;
    private final ApiErrorLogFrameworkService errorLogService;
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 顶级异常捕获并统一处理，当其他异常无法处理时候选择使用
     * 主要是提供给Filter使用
     * 因为Filter不走SpringMVC的流程，但是我们又需要兜底处理异常，所以这里提供一个全量的异常处理过程，保存逻辑统一
     */
    @ExceptionHandler({Exception.class})
    public Result<?> handle(HttpServletRequest request, Throwable exception) {
        if (exception instanceof MissingServletRequestParameterException) {
            return missingServletRequestParameterExceptionHandler((MissingServletRequestParameterException) exception);
        }
        return Result.failed(exception.getMessage());
    }

    /**
     * 处理系统异常，兜底处理所有的一切
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result<?> defaultExceptionHandler(HttpServletRequest request, Throwable exception) {
        // 情况一： 处理表不存在的异常
        Result<?> result = handleTableNotExists(exception);
        if (result != null) {
            return result;
        }
        // 情况二：部分特殊的库的处理
        if (Objects.equals("io.github.resilience4j.ratelimiter.RequestNotPermitted", exception.getClass().getName())) {
            return requestNotPermittedExceptionHandler(request, exception);
        }
        // 情况三：处理异常
        log.error("[defaultExceptionHandler]", exception);
        // 插入异常日志
        this.createExceptionLog(request, exception);
        // 返回Result
        return Result.failed(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), ResultEnum.INTERNAL_SERVER_ERROR.getMessage());
    }

    private void createExceptionLog(HttpServletRequest request, Throwable throwable) {
        // 插入错误日志
        ApiErrorLog apiErrorLog = new ApiErrorLog();
        try {
            initExceptionLog(apiErrorLog, request, throwable);
            errorLogService.createApiErrorLog(apiErrorLog);
        } catch (Throwable th) {
            log.error("[createExceptionLog][url({}) log({}) 发生异常]", request.getRequestURL(), JsonUtils.toJsonString(apiErrorLog), th);
        }
    }

    private void initExceptionLog(ApiErrorLog errorLog, HttpServletRequest request, Throwable e) {

    }

    /**
     * 处理Table不存在的异常情况
     *
     * @param throwable
     * @return
     */
    private Result<?> handleTableNotExists(Throwable throwable) {
        String message = ExceptionUtil.getRootCauseMessage(throwable);
        if (!message.contains("doesn't exist")) {
            return null;
        }
        // 1.数据报表
        if (message.contains("report_")) {
            log.error("[报表模块 gmall-module-report - 表结构未导入]");
            return Result.failed(ResultEnum.NOT_IMPLEMENTED.getCode(), "[报表模块 gmall-module-report - 表结构未导入]");
        }
        // TODO
        return null;
    }

    /**
     * 处理Resilience4j限流抛出的异常
     *
     * @param request
     * @param throwable
     * @return
     */
    public Result<?> requestNotPermittedExceptionHandler(HttpServletRequest request, Throwable throwable) {
        log.warn("[requestNotPermittedExceptionHandler][url({}) 访问过于频繁]", request.getRequestURL(), throwable);
        return Result.failed(TOO_MANY_REQUESTS);
    }

    /**
     * 捕获 {@code ServiceException} 异常
     */
    @ExceptionHandler({ServiceException.class})
    public Result<?> handleBusinessException(ServiceException ex) {
        return Result.failed(ex.getMessage());
    }

    /**
     * {@code @RequestBody} 参数校验不通过时抛出的异常处理
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.info("数据校验出现问题：{}，异常类型：{}", ex.getMessage(), ex.getClass());
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        if (StringUtils.hasText(msg)) {
            return Result.failed(ResultEnum.VALIDATE_FAILED.getCode(), msg);
        }
        return Result.failed(ResultEnum.VALIDATE_FAILED);
    }

    /**
     * {@code @PathVariable} 和 {@code @RequestParam} 参数校验不通过时抛出的异常处理
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Result<?> handleConstraintViolationException(ConstraintViolationException ex) {
        if (StringUtils.hasText(ex.getMessage())) {
            return Result.failed(ResultEnum.VALIDATE_FAILED.getCode(), ex.getMessage());
        }
        return Result.failed(ResultEnum.VALIDATE_FAILED);
    }

    /**
     * 处理SpringMVC请求参数缺失
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     *
     * @param exception 异常
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException exception) {
        log.warn("[missingServletRequestParameterExceptionHandler]", exception);
        return Result.failed(BAD_REQUEST.code(), String.format("请求参数缺失:%s", exception.getParameterName()));
    }
}
