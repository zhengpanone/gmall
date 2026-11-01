package com.zp.gmall.framework.validation.validator.base;

public abstract class AbstractValidator {

    /**
     * message 模板
     */
    protected String messageTemplate;

    /**
     * 构建 message
     */
    protected String buildMessage(
            String value,
            String allowed) {

        return messageTemplate
                .replace("{value}", value)
                .replace("{allowed}", allowed);
    }
}