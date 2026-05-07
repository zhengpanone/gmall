package com.zp.gmall.framework.common.validation.inenum;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

        Set<String> values = collection.stream()
                .filter(Objects::nonNull)
                .map(this::normalize)
                .collect(Collectors.toSet());

        if (allowedValues.containsAll(values)) {
            return true;
        }

        context.disableDefaultConstraintViolation();

        context.buildConstraintViolationWithTemplate(
                buildMessage(String.join(",", values))
        ).addConstraintViolation();

        return false;
    }
}

