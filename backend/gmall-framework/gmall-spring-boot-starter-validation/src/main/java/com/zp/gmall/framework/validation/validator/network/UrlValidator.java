package com.zp.gmall.framework.validation.validator.network;

import com.zp.gmall.framework.validation.annotation.network.Url;
import com.zp.gmall.framework.validation.constant.RegexConstants;
import com.zp.gmall.framework.validation.validator.base.AbstractRegexValidator;

import java.util.regex.Pattern;

/**
 * URL 格式校验器
 */
public class UrlValidator extends AbstractRegexValidator<Url> {

    @Override
    public void initialize(Url constraintAnnotation) {
        this.pattern = Pattern.compile(RegexConstants.URL);
        this.allowBlank = !constraintAnnotation.required();
    }
}
