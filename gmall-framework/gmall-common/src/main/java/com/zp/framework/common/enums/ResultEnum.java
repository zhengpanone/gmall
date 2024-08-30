
package com.zp.framework.common.enums;

import com.zp.framework.common.pojo.IResult;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum ResultEnum implements IResult {
    SUCCESS(0, "接口调用成功"),
    VALIDATE_FAILED(2002, "参数校验失败"),
    COMMON_FAILED(2003, "接口调用失败"),

    HAS_USERNAME(2005, "已存在改用户"),




    // ========== 服务端错误段 ==========

    INTERNAL_SERVER_ERROR(500, "系统异常"),

    NOT_IMPLEMENTED(501, "功能未实现/未开启"),

    // ========== 自定义错误段 ==========
    REPEATED_REQUESTS(900, "重复请求，请稍后重试"), // 重复请求

    DEMO_DENY(901, "演示模式，禁止写操作"),

    UNKNOWN(999, "未知错误"),
    ;


    private final Integer code;
    private final String message;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

