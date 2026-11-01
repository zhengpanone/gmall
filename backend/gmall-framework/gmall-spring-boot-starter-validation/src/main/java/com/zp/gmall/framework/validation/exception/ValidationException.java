package com.zp.gmall.framework.validation.exception;

import com.zp.gmall.framework.common.exception.ErrorCode;
import com.zp.gmall.framework.common.exception.enums.GlobalErrorCodeConstants;
import lombok.Getter;

import java.io.Serial;

/**
 * 参数校验异常
 */
@Getter
public class ValidationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Integer code;

    public ValidationException(String message) {
        super(message);
        this.code = GlobalErrorCodeConstants.BAD_REQUEST.getCode();
    }

    public ValidationException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ValidationException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }
}
