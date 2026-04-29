package com.gmall.module.bpm.constant;

import com.zp.gmall.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {

    // ==========  通用流程处理 模块 1-009-000-000 ==========

    // ========== OA 流程模块 1-009-001-000 ==========
    ErrorCode OA_LEAVE_NOT_EXISTS = new ErrorCode(1_009_001_001, "请假申请不存在");

    // ========== 流程模型 1-009-002-000 ==========
    ErrorCode MODEL_KEY_EXISTS = new ErrorCode(1_009_002_000, "已经存在流程标识为【{}】的流程");
    ErrorCode MODEL_NOT_EXISTS = new ErrorCode(1_009_002_001, "流程模型不存在");
}
