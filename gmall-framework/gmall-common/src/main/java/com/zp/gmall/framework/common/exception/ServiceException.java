package com.zp.gmall.framework.common.exception;

import com.zp.gmall.framework.common.exception.enums.ServiceErrorCodeRange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 业务逻辑异常Exception
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public final class ServiceException extends RuntimeException {
    /**
     * 业务错误码
     *
     * @see ServiceErrorCodeRange
     */
    private int code;
    /**
     * 错误提示
     */
    private String message;


    public ServiceException(ErrorCode errorCode) {
        this.code = errorCode.code();
        this.message = errorCode.msg();
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException setCode(Integer code) {
        this.code = code;
        return this;
    }

    public ServiceException setMessage(String message) {
        this.message = message;
        return this;
    }


}