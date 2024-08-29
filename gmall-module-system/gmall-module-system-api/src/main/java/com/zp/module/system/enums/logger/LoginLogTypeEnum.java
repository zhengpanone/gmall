package com.zp.module.system.enums.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author : zhengpanone
 * Date : 2023/11/13 22:51
 * Version : v1.0.0
 * Description: 登录日志的枚举类
 */
@Getter
@AllArgsConstructor
public enum LoginLogTypeEnum {
    // 使用账号登录
    LOGIN_USERNAME(100),
    // 使用社交登录
    LOGIN_SOCIAL(101),
    // 使用手机登录
    LOGIN_MOBILE(103),
    // 使用短信登录
    LOGIN_SMS(104),
    // 自己主动登出
    LOGOUT_SELF(200),
    // 强制退出
    LOGOUT_DELETE(202),
    ;

    /**
     * 日志类型
     */
    private final Integer type;
}
