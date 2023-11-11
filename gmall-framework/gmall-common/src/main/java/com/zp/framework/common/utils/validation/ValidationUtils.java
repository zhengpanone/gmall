package com.zp.framework.common.utils.validation;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

/**
 * Author : zhengpanone
 * Date : 2023/11/11 17:54
 * Version : v1.0.0
 * Description: 校验工具类
 */
public class ValidationUtils {
    public static void validate(Object object, Class<?>... groups) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Assert.notNull(validator);
        validate(validator, object, groups);
    }

    public static void validate(Validator validator, Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constrainViolations = validator.validate(object, groups);
        if (CollUtil.isNotEmpty(constrainViolations)) {
            throw new ConstraintViolationException(constrainViolations);
        }
    }
}
