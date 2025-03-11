package com.zp.gmall.framework.web.core.handler;

import com.zp.gmall.framework.common.exception.ServiceException;
import com.zp.gmall.framework.common.pojo.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
