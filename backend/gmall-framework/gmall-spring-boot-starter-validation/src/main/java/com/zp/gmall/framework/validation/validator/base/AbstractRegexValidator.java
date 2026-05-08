package com.zp.gmall.framework.validation.validator.base;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.framework.validation.validator.base
 * <p>
 * Description: 统一 Regex 校验
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-07
 */
public abstract class AbstractRegexValidator<A extends Annotation>
        extends AbstractValidator
        implements ConstraintValidator<A, String> {

    /**
     * 正则
     */
    protected Pattern pattern;

    /**
     * 是否允许空
     */
    protected boolean allowBlank = true;

    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext context) {

        if (value == null || value.trim().isEmpty()) {
            return allowBlank;
        }

        return pattern.matcher(value).matches();
    }
}
