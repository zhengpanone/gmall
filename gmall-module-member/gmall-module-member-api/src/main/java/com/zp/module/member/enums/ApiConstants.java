package com.zp.module.member.enums;

import com.zp.framework.common.enums.RpcConstants;

/**
 * Author : zhengpanone
 * Date : 2024/7/31 16:54
 * Version : v1.0.0
 * Description: API 相关的枚举
 */
public class ApiConstants {
    /**
     * 服务名
     * <p>
     * 注意，需要保证和 spring.application.name 保持一致
     */
    public static final String NAME = "member-server";

    public static final String PREFIX = RpcConstants.RPC_API_PREFIX + "/member";
    public static final String VERSION = "1.0.0";

}
