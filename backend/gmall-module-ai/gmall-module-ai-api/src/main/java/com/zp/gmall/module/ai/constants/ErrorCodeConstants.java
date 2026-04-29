package com.zp.gmall.module.ai.constants;

import com.zp.gmall.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    // ========== API 密钥 1-040-000-000 ==========
    ErrorCode API_KEY_NOT_EXISTS = new ErrorCode(1_040_000_000, "API 密钥不存在");
    ErrorCode API_KEY_DISABLE = new ErrorCode(1_040_000_001, "API 密钥已禁用！");
}
