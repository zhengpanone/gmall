package com.zp.gmall.module.mp.constant;

import com.zp.gmall.framework.common.exception.ErrorCode;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:24
 * Version : v1.0.0
 * Description: 错误码枚举类
 */
public interface ErrorCodeConstants {
    // ========== 公众号账号 1-006-000-000 ============
    ErrorCode ACCOUNT_NOT_EXISTS = new ErrorCode(1_006_000_000, "公众号账号不存在");
    ErrorCode ACCOUNT_GENERATE_QR_CODE_FAIL = new ErrorCode(1_006_000_001, "生成公众号二维码失败，原因：{}");
    ErrorCode ACCOUNT_CLEAR_QUOTA_FAIL = new ErrorCode(1_006_000_002, "清空公众号的 API 配额失败，原因：{}");

}
