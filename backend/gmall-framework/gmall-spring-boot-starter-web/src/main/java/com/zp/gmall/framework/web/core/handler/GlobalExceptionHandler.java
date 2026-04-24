package com.zp.gmall.framework.web.core.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.zp.gmall.framework.common.exception.ServiceException;
import com.zp.gmall.framework.common.pojo.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.zp.gmall.framework.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 20:28
 * Version : v1.0.0
 * Description:
 */
@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = ServiceException.class)
    public Result<?> serviceException(ServiceException exception) {
        return Result.failed(exception.getCode(), exception.getMessage());
    }

    /**
     * 参数校验异常
     * @param exception 异常
     * @return 结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<?> exception(MethodArgumentNotValidException exception) {
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
}
