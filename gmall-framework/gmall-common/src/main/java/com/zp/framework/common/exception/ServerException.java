package com.zp.framework.common.exception;

import com.zp.framework.common.exception.enums.GlobalErrorCodeConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 服务器异常 Exception
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerException extends RuntimeException {
    /**
     * 全局错误码
     *
     * @see GlobalErrorCodeConstants
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String msg;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServerException(String msg) {
        super(msg);
    }

    public ServerException(ErrorCode errorCode) {
        this.code = errorCode.code();
        this.msg = errorCode.message();
    }


    public ServerException(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    public Integer getCode() {
        return code;
    }

    public ServerException setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    public ServerException setMessage(String message) {
        this.msg = message;
        return this;
    }


}
