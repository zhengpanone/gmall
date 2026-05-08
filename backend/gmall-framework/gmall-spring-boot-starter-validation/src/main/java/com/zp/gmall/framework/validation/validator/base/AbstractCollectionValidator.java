package com.zp.gmall.framework.validation.validator.base;

import java.util.Collection;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.framework.validation.validator.base
 * <p>
 * Description: 统一 Collection 校验
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-07
 */
public abstract class AbstractCollectionValidator extends AbstractValidator {

    /**
     * 是否允许 null
     */
    protected boolean allowNull;

    /**
     * 是否允许 empty
     */
    protected boolean allowEmpty = true;

    /**
     * 统一处理空值逻辑。
     *
     * @return null 表示继续执行后续元素校验；非 null 表示可直接返回校验结果
     */
    protected Boolean validateNullableCollection(Collection<?> collection) {
        if (collection == null) {
            return allowNull;
        }
        if (collection.isEmpty()) {
            return allowEmpty;
        }
        return null;
    }
}
