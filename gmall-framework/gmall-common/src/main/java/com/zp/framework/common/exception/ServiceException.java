package com.zp.framework.common.exception;

import com.zp.framework.common.exception.enums.ServiceErrorCodeRange;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务逻辑异常Exception
 */
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

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException() {
    }

    public ServiceException(ErrorCode errorCode) {
        this.code = errorCode.code();
        this.message = errorCode.message();
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException setCode(Integer code) {
        this.code = code;
        return this;
    }

    public ServiceException setMsg(String message) {
        this.message = message;
        return this;
    }

    public ServiceException setCode(int code) {
        this.code = code;
        return this;
    }

}
