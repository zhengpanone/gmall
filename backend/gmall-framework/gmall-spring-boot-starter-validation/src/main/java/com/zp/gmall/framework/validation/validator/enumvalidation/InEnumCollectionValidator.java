package com.zp.gmall.framework.validation.validator.enumvalidation;


import com.zp.gmall.framework.validation.annotation.enumvalidation.InEnum;
import com.zp.gmall.framework.validation.validator.base.AbstractInEnumValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class InEnumCollectionValidator extends AbstractInEnumValidator implements ConstraintValidator<InEnum, Collection<?>> {

    @Override
    public void initialize(InEnum annotation) {
        init(annotation);
    }

    @Override
    public boolean isValid(
            Collection<?> collection,
            ConstraintValidatorContext context) {

        if (collection == null) {
            return allowNull;
        }

        if (collection.isEmpty()) {
            return allowEmpty;
        }

        Set<String> invalidValues = new LinkedHashSet<>();
        for (Object item : collection) {
            if (item == null) {
                if (!allowNull) {
                    invalidValues.add("null");
                }
                continue;
            }
            String value = normalize(item);
            if (!allowedValues.contains(value)) {
                invalidValues.add(String.valueOf(item));
            }
        }

        if (invalidValues.isEmpty()) {
            return true;
        }

        context.disableDefaultConstraintViolation();

        context.buildConstraintViolationWithTemplate(
                buildMessage(String.join(",", invalidValues))
        ).addConstraintViolation();

        return false;
    }
}
