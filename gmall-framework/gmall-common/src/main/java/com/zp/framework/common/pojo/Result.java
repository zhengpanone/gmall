package com.zp.framework.common.pojo;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zp.framework.common.enums.ResultEnum;
import com.zp.framework.common.exception.ErrorCode;
import com.zp.framework.common.exception.ServiceException;
import com.zp.framework.common.utils.MessageUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * 统一返回数据结构
 *
 * @author zhengpanon
 * @since 1.0.0
 */
@Schema(description = "返回结果集")
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
        return instance(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

    public static <T> Result<T> ok(T data) {
        return instance(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> ok(String message, T data) {
        return instance(ResultEnum.SUCCESS.getCode(), message, data);
    }


    public static Result<?> failed() {
        return instance(ResultEnum.COMMON_FAILED.getCode(), ResultEnum.COMMON_FAILED.getMessage(), null);
    }

    public static Result<?> failed(ErrorCode errorCode) {
        return instance(errorCode.code(), errorCode.message(),null);
    }

    public static Result<?> failed(int code) {
        return new Result<>(code, MessageUtils.getMessage(code));
    }

    public static Result<?> failed(String msg) {
        return instance(ResultEnum.COMMON_FAILED.getCode(), msg, null);
    }

    public static Result<?> failed(int code, String msg) {
        Assert.isTrue(!ResultEnum.SUCCESS.getCode().equals(code), "code值必须是错误值");

        return instance(code, msg, null);
    }

    public static Result<?> failed(IResult iResult) {
        return instance(iResult.getCode(), iResult.getMessage(), null);
    }

    public static <T> Result<T> instance(Integer code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    public static boolean isSuccess(Integer code) {
        return Objects.equals(ResultEnum.SUCCESS.getCode(), code);
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

}
