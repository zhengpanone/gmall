package com.zp.gmall.framework.validation.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.framework.validation.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Set;

import static com.zp.gmall.framework.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;

/**
 * 全局参数校验异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalValidationExceptionHandler {

    /**
     * 处理 @RequestBody / @ModelAttribute 的绑定校验异常
     */
    @ExceptionHandler(BindException.class)
    public Result<?> bindException(BindException exception) {
        log.warn("[bindException]", exception);
        String errorMessage = getBindingResultErrorMessage(exception.getBindingResult());
        if (StrUtil.isBlank(errorMessage)) {
            return Result.failed(BAD_REQUEST);
        }
        return Result.failed(BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", errorMessage));
    }

    /**
     * 处理 @Validated 方法参数异常，例如 @RequestParam
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<?> constraintViolationException(ConstraintViolationException exception) {
        log.warn("[constraintViolationException]", exception);
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        if (CollUtil.isEmpty(violations)) {
            return Result.failed(BAD_REQUEST);
        }
        String errorMessage = violations.iterator().next().getMessage();
        if (StrUtil.isBlank(errorMessage)) {
            return Result.failed(BAD_REQUEST);
        }
        return Result.failed(BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", errorMessage));
    }

    @ExceptionHandler(ValidationException.class)
    public Result<?> validationException(ValidationException exception) {
        return Result.failed(exception.getCode(), exception.getMessage());
    }

    private String getBindingResultErrorMessage(BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError();
        if (fieldError != null) {
            return fieldError.getDefaultMessage();
        }
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if (CollUtil.isEmpty(allErrors)) {
            return null;
        }
        return allErrors.get(0).getDefaultMessage();
    }
}
