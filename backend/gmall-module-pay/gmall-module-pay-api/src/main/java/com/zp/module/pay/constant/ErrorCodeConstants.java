package com.zp.module.pay.constant;

import com.zp.gmall.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    // ========== APP 模块 1-007-000-000 ==========
    ErrorCode APP_NOT_FOUND = new ErrorCode(1_007_000_000, "App 不存在");
    ErrorCode APP_IS_DISABLE = new ErrorCode(1_007_000_002, "App 已经被禁用");
}
