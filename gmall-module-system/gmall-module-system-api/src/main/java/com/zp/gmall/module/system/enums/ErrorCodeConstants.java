package com.zp.gmall.module.system.enums;

import com.zp.gmall.framework.common.exception.ErrorCode;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 19:46
 * Version : v1.0.0
 * Description:
 */
public interface ErrorCodeConstants {
    // ========== AUTH 模块 1-002-000-000 ==========
    ErrorCode AUTH_LOGIN_BAD_CREDENTIALS = new ErrorCode(1_002_000_000, "登录失败，账号密码不正确");
}
