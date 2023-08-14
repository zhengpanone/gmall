/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.zp.common.utils;

import com.zp.common.enums.ResultEnum;
import com.zp.common.exception.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一返回数据结构
 *
 * @author 
 * @since 1.0.0
 */
@ApiModel(value = "返回结果集")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编码：0表示成功，其他值表示失败
     */
    @ApiModelProperty(value = "编码：0表示成功，其他值表示失败")
    private Integer code;
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String msg;
    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据")
    private T data;

    public Result(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    public static Result<?> ok() {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> ok(String message, T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), message, data);
    }


    public static Result<?> failed() {
        return new Result<>(ResultEnum.COMMON_FAILED.getCode(), ResultEnum.COMMON_FAILED.getMessage(), null);
    }

    public static Result<?> failed(int code) {
        return new Result<>(code, MessageUtils.getMessage(code));
    }

    public static Result<?> failed(String msg) {
        return new Result<>(ResultEnum.COMMON_FAILED.getCode(), msg, null);
    }

    public static Result<?> failed(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static Result<?> failed(IResult iResult) {
        return new Result<>(iResult.getCode(), iResult.getMessage(), null);
    }

    public static <T> Result<T> instance(Integer code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

}
