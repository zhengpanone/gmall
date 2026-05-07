package com.zp.gmall.framework.common.validation.inenum;

import com.zp.gmall.framework.common.util.enums.EnumUtils;

import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.framework.common.validation.inenum
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-07
 */
public abstract class AbstractInEnumValidator {
    /**
     * 允许值
     */
    protected Set<String> allowedValues;

    /**
     * 允许值文本
     */
    protected String allowedValuesText;

    /**
     * 是否允许 null
     */
    protected boolean allowNull;

    /**
     * 是否允许 empty
     */
    protected boolean allowEmpty;

    /**
     * 是否忽略大小写
     */
    protected boolean ignoreCase;

    /**
     * 错误模板
     */
    protected String messageTemplate;

    protected void init(InEnum annotation) {

        this.allowNull = annotation.allowNull();
        this.allowEmpty = annotation.allowEmpty();
        this.ignoreCase = annotation.ignoreCase();
        this.messageTemplate = annotation.message();

        this.allowedValues = EnumUtils.getEnumValues(
                annotation.enumClass(),
                ignoreCase
        );

        this.allowedValuesText = allowedValues.stream()
                .sorted()
                .collect(Collectors.joining(","));
    }

    /**
     * 规范化
     */
    protected String normalize(Object value) {

        String str = String.valueOf(value);

        return ignoreCase
                ? str.toLowerCase()
                : str;
    }

    /**
     * 构建错误消息
     */
    protected String buildMessage(String value) {

        return messageTemplate
                .replace("{value}", value)
                .replace("{allowed}", allowedValuesText);
    }
}
