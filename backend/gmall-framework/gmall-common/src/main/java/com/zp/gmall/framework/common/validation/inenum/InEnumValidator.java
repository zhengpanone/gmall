package com.zp.gmall.framework.common.validation.inenum;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 单值验证器
 */
public class InEnumValidator extends AbstractInEnumValidator implements ConstraintValidator<InEnum, Object> {


    @Override
    public void initialize(InEnum annotation) {
        init(annotation);
    }

    @Override
    public boolean isValid(
            Object value,
            ConstraintValidatorContext context) {

        if (value == null) {
            return allowNull;
        }

        String strValue = normalize(value);

        if (allowedValues.contains(strValue)) {
            return true;
        }

        context.disableDefaultConstraintViolation();

        context.buildConstraintViolationWithTemplate(
                buildMessage(strValue)
        ).addConstraintViolation();

        return false;
    }

}

