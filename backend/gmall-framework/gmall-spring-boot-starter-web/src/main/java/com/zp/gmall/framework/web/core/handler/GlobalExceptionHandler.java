package com.zp.gmall.framework.web.core.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.zp.gmall.framework.common.biz.infra.logger.ApiErrorLogCommonApi;
import com.zp.gmall.framework.common.biz.infra.logger.dto.ApiErrorLogCreateReqDTO;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.framework.common.exception.ServerException;
import com.zp.gmall.framework.common.exception.ServiceException;
import com.zp.gmall.framework.common.util.json.JsonUtils;
import com.zp.gmall.framework.common.util.monitor.TracerUtils;
import com.zp.gmall.framework.common.util.servlet.ServletUtils;
import com.zp.gmall.framework.web.util.WebFrameworkUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.zp.gmall.framework.common.exception.enums.GlobalErrorCodeConstants.*;


/**
 * Author : zhengpanone
 * Date : 2025/4/7 20:28
 * Version : v1.0.0
 * Description:
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final String applicationName;

    private final ApiErrorLogCommonApi apiErrorLogApi;

    /**
     * 处理所有异常，主要是提供给 Filter 使用
     * 因为 Filter 不走 SpringMVC 的流程，但是我们又需要兜底处理异常，所以这里提供一个全量的异常处理过程，保持逻辑统一。
     *
     * @param request 请求
     * @param ex      异常
     * @return 通用返回
     */
    public Result<?> allExceptionHandler(HttpServletRequest request, Throwable ex) {
//        if (ex instanceof MissingServletRequestParameterException) {
//            return missingServletRequestParameterExceptionHandler((MissingServletRequestParameterException) ex);
//        }
//        if (ex instanceof MethodArgumentTypeMismatchException) {
//            return methodArgumentTypeMismatchExceptionHandler((MethodArgumentTypeMismatchException) ex);
//        }
        if (ex instanceof MethodArgumentNotValidException) {
            return methodArgumentNotValidExceptionExceptionHandler((MethodArgumentNotValidException) ex);
        }
//        if (ex instanceof BindException) {
//            return bindExceptionHandler((BindException) ex);
//        }
//        if (ex instanceof ConstraintViolationException) {
//            return constraintViolationExceptionHandler((ConstraintViolationException) ex);
//        }
//        if (ex instanceof ValidationException) {
//            return validationException((ValidationException) ex);
//        }
//        if (ex instanceof MaxUploadSizeExceededException) {
//            return maxUploadSizeExceededExceptionHandler((MaxUploadSizeExceededException) ex);
//        }
//        if (ex instanceof NoHandlerFoundException) {
//            return noHandlerFoundExceptionHandler((NoHandlerFoundException) ex);
//        }
//        if (ex instanceof NoResourceFoundException) {
//            return noResourceFoundExceptionHandler(request, (NoResourceFoundException) ex);
//        }
//        if (ex instanceof HttpRequestMethodNotSupportedException) {
//            return httpRequestMethodNotSupportedExceptionHandler((HttpRequestMethodNotSupportedException) ex);
//        }
//        if (ex instanceof HttpMediaTypeNotSupportedException) {
//            return httpMediaTypeNotSupportedExceptionHandler((HttpMediaTypeNotSupportedException) ex);
//        }
        if (ex instanceof ServiceException) {
            return serviceExceptionHandler((ServiceException) ex);
        }
        if (ex instanceof AccessDeniedException) {
            return accessDeniedExceptionHandler(request, (AccessDeniedException) ex);
        }
        return defaultExceptionHandler(request, ex);
    }


    /**
     * 处理系统异常，兜底处理所有的一切
     */
    @ExceptionHandler(value = Exception.class)
    public Result<?> defaultExceptionHandler(HttpServletRequest req, Throwable ex) {
        // 特殊：如果是 ServiceException 的异常，则直接返回
        // 例如说：https://gitee.com/zhijiantianya/yudao-cloud/issues/ICSSRM、https://gitee.com/zhijiantianya/yudao-cloud/issues/ICT6FM
        if (ex.getCause() != null && ex.getCause() instanceof ServiceException) {
            return serviceExceptionHandler((ServiceException) ex.getCause());
        }

        // 情况一：处理表不存在的异常
        Result<?> tableNotExistsResult = handleTableNotExists(ex);
        if (tableNotExistsResult != null) {
            return tableNotExistsResult;
        }

        // 情况二：处理异常
        log.error("[defaultExceptionHandler]", ex);
        // 插入异常日志
        createExceptionLog(req, ex);
        // 返回 ERROR CommonResult
        return Result.failed(INTERNAL_SERVER_ERROR.getCode(), INTERNAL_SERVER_ERROR.getMsg());
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
     * 处理业务异常
     *
     * @param exception 异常
     * @return 结果
     * 如：商品库存不足，用户手机号已存在。
     */
    @ExceptionHandler(value = ServiceException.class)
    public Result<?> serviceExceptionHandler(ServiceException exception) {
        return Result.failed(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(value = ServerException.class)
    public Result<?> serverExceptionHandler(ServerException exception) {
        return Result.failed(exception.getCode(), exception.getMessage());
    }

    /**
     * 参数校验异常
     *
     * @param exception 异常
     * @return 结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<?> methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException exception) {
        log.warn("[methodArgumentNotValidExceptionExceptionHandler]", exception);
        // 获取 errorMessage
        String errorMessage = null;
        FieldError fieldError = exception.getBindingResult().getFieldError();
        if (fieldError == null) {
            // 组合校验，参考自 https://t.zsxq.com/3HVTx
            List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
            if (CollUtil.isNotEmpty(allErrors)) {
                errorMessage = allErrors.get(0).getDefaultMessage();
            }
        } else {
            errorMessage = fieldError.getDefaultMessage();
        }
        // 转换 CommonResult
        if (StrUtil.isEmpty(errorMessage)) {
            return Result.failed(BAD_REQUEST);
        }
        return Result.failed(BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", errorMessage));
    }

    private void createExceptionLog(HttpServletRequest req, Throwable e) {
        // 插入错误日志
        ApiErrorLogCreateReqDTO errorLog = new ApiErrorLogCreateReqDTO();
        try {
            // 初始化 errorLog
            buildExceptionLog(errorLog, req, e);
            // 执行插入 errorLog
            apiErrorLogApi.createApiErrorLogAsync(errorLog);
        } catch (Throwable th) {
            log.error("[createExceptionLog][url({}) log({}) 发生异常]", req.getRequestURI(), JsonUtils.toJsonString(errorLog), th);
        }
    }


    private void buildExceptionLog(ApiErrorLogCreateReqDTO errorLog, HttpServletRequest request, Throwable e) {
        // 处理用户信息
        errorLog.setUserId(WebFrameworkUtils.getLoginUserId(request));
        errorLog.setUserType(WebFrameworkUtils.getLoginUserType(request));
        // 设置异常字段
        errorLog.setExceptionName(e.getClass().getName());
        errorLog.setExceptionMessage(ExceptionUtil.getMessage(e));
        errorLog.setExceptionRootCauseMessage(ExceptionUtil.getRootCauseMessage(e));
        errorLog.setExceptionStackTrace(ExceptionUtil.stacktraceToString(e));
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        Assert.notEmpty(stackTraceElements, "异常 stackTraceElements 不能为空");
        StackTraceElement stackTraceElement = stackTraceElements[0];
        errorLog.setExceptionClassName(stackTraceElement.getClassName());
        errorLog.setExceptionFileName(stackTraceElement.getFileName());
        errorLog.setExceptionMethodName(stackTraceElement.getMethodName());
        errorLog.setExceptionLineNumber(stackTraceElement.getLineNumber());
        // 设置其它字段
        errorLog.setTraceId(TracerUtils.getTraceId());
        errorLog.setApplicationName(applicationName);
        errorLog.setRequestUrl(request.getRequestURI());
        Map<String, Object> requestParams = MapUtil.<String, Object>builder()
                .put("query", ServletUtils.getParamMap(request))
                .put("body", ServletUtils.getBody(request)).build();
        errorLog.setRequestParams(JsonUtils.toJsonString(requestParams));
        errorLog.setRequestMethod(request.getMethod());
        errorLog.setUserAgent(ServletUtils.getUserAgent(request));
        errorLog.setUserIp(ServletUtils.getClientIP(request));
        errorLog.setExceptionTime(LocalDateTime.now());
    }

    /**
     * 处理 Table 不存在的异常情况
     *
     * @param ex 异常
     * @return 如果是 Table 不存在的异常，则返回对应的 CommonResult
     */
    private Result<?> handleTableNotExists(Throwable ex) {
        String message = ExceptionUtil.getRootCauseMessage(ex);
        if (!message.contains("doesn't exist")) {
            return null;
        }
        // 1. 数据报表
        if (message.contains("report_")) {
            log.error("[报表模块 gmall-module-report - 表结构未导入]");
            return Result.failed(NOT_IMPLEMENTED.getCode(),
                    "[报表模块 gmall-module-report - 表结构未导入]");
        }
        // 2. 工作流
        if (message.contains("bpm_")) {
            log.error("[工作流模块 gmall-module-bpm - 表结构未导入]");
            return Result.failed(NOT_IMPLEMENTED.getCode(),
                    "[工作流模块 gmall-module-bpm - 表结构未导入]");
        }
        // 3. 微信公众号
        if (message.contains("mp_")) {
            log.error("[微信公众号 gmall-module-mp - 表结构未导入]");
            return Result.failed(NOT_IMPLEMENTED.getCode(),
                    "[微信公众号 gmall-module-mp - 表结构未导入]");
        }
        // 4. 商城系统
        if (StrUtil.containsAny(message, "product_", "promotion_", "trade_")) {
            log.error("[商城系统 gmall-module-mall - 已禁用]");
            return Result.failed(NOT_IMPLEMENTED.getCode(),
                    "[商城系统 gmall-module-mall - 已禁用]");
        }
        // 5. ERP 系统
        if (message.contains("erp_")) {
            log.error("[ERP 系统 gmall-module-erp - 表结构未导入]");
            return Result.failed(NOT_IMPLEMENTED.getCode(),
                    "[ERP 系统 gmall-module-erp - 表结构未导入]");
        }
        // 6. CRM 系统
        if (message.contains("crm_")) {
            log.error("[CRM 系统 gmall-module-crm - 表结构未导入]");
            return Result.failed(NOT_IMPLEMENTED.getCode(),
                    "[CRM 系统 gmall-module-crm - 表结构未导入]");
        }
        // 7. 支付平台
        if (message.contains("pay_")) {
            log.error("[支付模块 gmall-module-pay - 表结构未导入]");
            return Result.failed(NOT_IMPLEMENTED.getCode(),
                    "[支付模块 gmall-module-pay - 表结构未导入]");
        }
        // 8. AI 大模型
        if (message.contains("ai_")) {
            log.error("[AI 大模型 gmall-module-ai - 表结构未导入]");
            return Result.failed(NOT_IMPLEMENTED.getCode(),
                    "[AI 大模型 gmall-module-ai - 表结构未导入]");
        }
        // 9. IoT 物联网
        if (message.contains("iot_")) {
            log.error("[IoT 物联网 gmall-module-iot - 表结构未导入]");
            return Result.failed(NOT_IMPLEMENTED.getCode(),
                    "[IoT 物联网 gmall-module-iot - 表结构未导入]");
        }
        return null;
    }
}
