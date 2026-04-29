package com.zp.gmall.module.product.constant;

import com.zp.gmall.framework.common.exception.ErrorCode;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:58
 * Version : v1.0.0
 * Description: Product 错误码枚举类
 * product 系统，使用 1-008-000-000 段
 */
public class ErrorCodeConstants {
    // ========== 商品分类相关 1-008-001-000 ============
    ErrorCode CATEGORY_NOT_EXISTS = new ErrorCode(1_008_001_000, "商品分类不存在");
    ErrorCode CATEGORY_PARENT_NOT_EXISTS = new ErrorCode(1_008_001_001, "父分类不存在");
    ErrorCode CATEGORY_PARENT_NOT_FIRST_LEVEL = new ErrorCode(1_008_001_002, "父分类不能是二级分类");
    ErrorCode CATEGORY_EXISTS_CHILDREN = new ErrorCode(1_008_001_003, "存在子分类，无法删除");
    ErrorCode CATEGORY_DISABLED = new ErrorCode(1_008_001_004, "商品分类({})已禁用，无法使用");
    ErrorCode CATEGORY_HAVE_BIND_SPU = new ErrorCode(1_008_001_005, "类别下存在商品，无法删除");
}
