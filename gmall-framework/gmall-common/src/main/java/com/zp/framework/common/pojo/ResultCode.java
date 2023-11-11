package com.zp.framework.common.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Author : zhengpanone
 * Date : 2023/11/15 14:20
 * Version : v1.0.0
 
 */
@Data
@NotNull
@AllArgsConstructor
public class ResultCode implements IResult {
    private Integer code;
    private String message;

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
