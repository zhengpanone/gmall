package com.zp.module.system.enums;

import com.zp.framework.common.enums.RpcConstants;

/**
 * Author : zhengpanone
 * Date : 2024/1/10 11:26
 * Version : v1.0.0
 * Description: API相关的枚举
 */
public class ApiConstants {

    /**
     * 服务名
     *
     * 注意，需要保证和 spring.application.name 保持一致
     */
    public static final String NAME = "system-server";
    public static final String PREFIX = RpcConstants.RPC_API_PREFIX+"/system";
    public static final String VERSION = "1.0.0";
}
