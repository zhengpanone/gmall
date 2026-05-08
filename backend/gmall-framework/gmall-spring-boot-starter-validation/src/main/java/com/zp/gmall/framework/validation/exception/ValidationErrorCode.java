package com.zp.gmall.framework.validation.exception;

import com.zp.gmall.framework.common.exception.ErrorCode;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.framework.validation.exception
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-07
 */
public interface ValidationErrorCode {
    ErrorCode VALIDATION_ERROR = new ErrorCode(400, "请求参数不正确");

    ErrorCode MOBILE_ERROR = new ErrorCode(400, "手机号格式不正确");

    ErrorCode ENUM_ERROR = new ErrorCode(400, "参数枚举值不正确");
}
