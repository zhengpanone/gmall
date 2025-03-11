package com.zp.gmall.framework.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zp.gmall.framework.common.exception.ErrorCode;
import com.zp.gmall.framework.common.exception.ServiceException;
import com.zp.gmall.framework.common.exception.enums.GlobalErrorCodeConstants;
import com.zp.gmall.framework.common.util.MessageUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Author : zhengpanone
 * Date : 2025/3/27 13:10
 * Version : v1.0.0
 * Description:
 */
@Schema(description = "统一返回结果")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 编码：0表示成功，其他值表示失败
     */
    @Schema(description = "编码：0表示成功，其他值表示失败")
    private Integer code;
    /**
     * 消息内容
     */
    @Schema(description = "消息内容")
    private String msg;
    /**
     * 响应数据
     */
    @Schema(description = "响应数据")
    private T data;

    public Result(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    public static Result<?> ok() {
        return instance(GlobalErrorCodeConstants.SUCCESS.code(), GlobalErrorCodeConstants.SUCCESS.msg(), null);
    }

    public static <T> Result<T> ok(T data) {
        return instance(GlobalErrorCodeConstants.SUCCESS.code(), GlobalErrorCodeConstants.SUCCESS.msg(), data);
    }

    public static <T> Result<T> ok(String message, T data) {
        return instance(GlobalErrorCodeConstants.SUCCESS.code(), message, data);
    }


    public static Result<?> failed() {
        return instance(GlobalErrorCodeConstants.UNKNOWN.code(), GlobalErrorCodeConstants.UNKNOWN.msg(), null);
    }

    public static Result<?> failed(ErrorCode errorCode) {
        return instance(errorCode.code(), errorCode.msg(), null);
    }

    public static Result<?> failed(int code) {
        return new Result<>(code, MessageUtils.getMessage(code));
    }

    public static Result<?> failed(String msg) {
        return instance(GlobalErrorCodeConstants.UNKNOWN.code(), msg, null);
    }

    public static Result<?> failed(int code, String msg) {
        Assert.isTrue(!GlobalErrorCodeConstants.SUCCESS.code().equals(code), "code值必须是错误值");

        return instance(code, msg, null);
    }

    public static Result<?> failed(IResult iResult) {
        return instance(iResult.getCode(), iResult.getMsg(), null);
    }

    public static <T> Result<T> instance(Integer code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    public static boolean isSuccess(Integer code) {
        return Objects.equals(GlobalErrorCodeConstants.SUCCESS.code(), code);
    }

    @JsonIgnore// 避免 jackson 序列化
    public boolean isSuccess() {
        return isSuccess(code);
    }

    @JsonIgnore // 避免 jackson 序列化
    public boolean isError() {
        return !isSuccess();
    }

    // ========= 和 Exception 异常体系集成 =========
    public void checkError() {
        if (isSuccess()) {
            return;
        }
        // 业务异常
        throw new ServiceException(code, msg);
    }

    /**
     * 判断是否有异常。如果有，则抛出 {@link ServiceException} 异常
     * 如果没有，则返回 {@link #data} 数据
     */
    @JsonIgnore // 避免 jackson 序列化
    public T getCheckedData() {
        checkError();
        return data;
    }
}
