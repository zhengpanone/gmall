package com.zp.gmall.framework.validation.validator.file;

import com.zp.gmall.framework.validation.annotation.file.FileType;
import com.zp.gmall.framework.validation.validator.base.AbstractValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 文件后缀校验器
 */
public class FileTypeValidator
        extends AbstractValidator
        implements ConstraintValidator<FileType, String> {

    private Set<String> allowedExtensions;
    private String allowedExtensionsText;
    private boolean required;
    private boolean ignoreCase;

    @Override
    public void initialize(FileType annotation) {
        this.required = annotation.required();
        this.ignoreCase = annotation.ignoreCase();
        this.messageTemplate = annotation.message();

        this.allowedExtensions = Arrays.stream(annotation.value())
                .map(String::trim)
                .map(item -> item.startsWith(".") ? item.substring(1) : item)
                .map(this::normalize)
                .collect(Collectors.toSet());
        this.allowedExtensionsText = this.allowedExtensions.stream()
                .sorted()
                .collect(Collectors.joining(","));
    }

    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext context) {

        if (value == null || value.trim().isEmpty()) {
            return !required;
        }

        String extension = extractExtension(value);
        String normalizedExtension = normalize(extension);

        if (allowedExtensions.contains(normalizedExtension)) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                buildMessage(value, allowedExtensionsText)
        ).addConstraintViolation();
        return false;
    }

    private String extractExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot < 0 || lastDot >= fileName.length() - 1) {
            return "";
        }
        return fileName.substring(lastDot + 1);
    }

    private String normalize(String value) {
        return ignoreCase ? value.toLowerCase(Locale.ROOT) : value;
    }
}
