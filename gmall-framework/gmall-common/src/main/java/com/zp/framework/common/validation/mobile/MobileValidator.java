package com.zp.framework.common.validation.mobile;

import com.zp.framework.common.util.validation.ValidationUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

//注解校验器
public class MobileValidator implements ConstraintValidator<Mobile, String> {
    private boolean required = false;
    /**
     * 在验证开始前调用注解里的方法，从而获取到一些注解里的参数
     * <p>
     * 判断参数是否合法
     */

    @Override
    public void initialize(Mobile constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }


    /**
     * 判断参数是否合法
     */

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (this.required || StringUtils.hasText(value)) {
            // 验证
            return ValidationUtils.isMobile(value);
        }
        return true;
    }
}

