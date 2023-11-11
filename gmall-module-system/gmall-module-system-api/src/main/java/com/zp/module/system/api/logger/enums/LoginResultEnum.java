package com.zp.module.system.api.logger.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author : zhengpanone
 * Date : 2023/11/13 22:57
 * Version : v1.0.0
 * Description: 登录结果的枚举类
 */
@Getter
@AllArgsConstructor
public enum LoginResultEnum {
    SUCCESS(0),// 成功
    BAD_CREDENTIALS(10), // 账号或密码不正取
    USER_DISABLED(20), // 用户被禁用
    CAPTCHA_NOT_FOUND(30),// 图片验证码不存在
    CAPTCHA_CODE_ERROR(31),// 图片验证码不正确
    ;


    private final Integer result;
}
