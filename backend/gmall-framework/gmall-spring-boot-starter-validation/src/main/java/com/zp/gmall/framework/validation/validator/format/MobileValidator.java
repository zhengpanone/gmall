package com.zp.gmall.framework.validation.validator.format;

import com.zp.gmall.framework.validation.annotation.format.Mobile;
import com.zp.gmall.framework.validation.constant.RegexConstants;
import com.zp.gmall.framework.validation.validator.base.AbstractRegexValidator;

import java.util.regex.Pattern;

/**
 * 手机号校验器
 */
public class MobileValidator extends AbstractRegexValidator<Mobile> {

    @Override
    public void initialize(Mobile constraintAnnotation) {
        this.pattern = Pattern.compile(RegexConstants.MOBILE);
        this.allowBlank = !constraintAnnotation.required();
    }
}
