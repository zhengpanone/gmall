package com.zp.gmall.module.trade.constant;

import com.zp.gmall.framework.common.exception.ErrorCode;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 00:15
 * Version : v1.0.0
 * Description:
 */
public interface ErrorCodeConstants {
    // ========== Order 模块 1-011-000-000 ==========
    ErrorCode ORDER_ITEM_NOT_FOUND = new ErrorCode(1_011_000_010, "交易订单项不存在");
    ErrorCode ORDER_NOT_FOUND = new ErrorCode(1_011_000_011, "交易订单不存在");
}
