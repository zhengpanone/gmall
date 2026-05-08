package com.zp.gmall.framework.validation;

import com.zp.gmall.framework.common.core.Valuable;
import com.zp.gmall.framework.validation.annotation.enumvalidation.InEnum;
import com.zp.gmall.framework.validation.annotation.file.FileType;
import com.zp.gmall.framework.validation.annotation.format.Mobile;
import com.zp.gmall.framework.validation.annotation.network.Url;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationAnnotationsTest {

    private static final Validator VALIDATOR =
            Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldPassWhenAllFieldsValid() {
        DemoRequest request = new DemoRequest();
        request.mobile = "13800138000";
        request.url = "https://example.com/path";
        request.fileName = "avatar.JPG";
        request.status = 1;
        request.statusList = List.of(1, 2);

        Set<ConstraintViolation<DemoRequest>> violations = VALIDATOR.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldRejectInvalidMobile() {
        DemoRequest request = new DemoRequest();
        request.mobile = "123456";
        request.url = "https://example.com";
        request.fileName = "avatar.jpg";
        request.status = 1;
        request.statusList = List.of(1);

        Set<ConstraintViolation<DemoRequest>> violations = VALIDATOR.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(containsProperty(violations, "mobile"));
    }

    @Test
    void shouldAllowBlankForNonRequiredUrl() {
        DemoRequest request = new DemoRequest();
        request.mobile = "13800138000";
        request.url = "";
        request.fileName = "avatar.jpg";
        request.status = 1;
        request.statusList = List.of(1);

        Set<ConstraintViolation<DemoRequest>> violations = VALIDATOR.validate(request);
        assertFalse(containsProperty(violations, "url"));
    }

    @Test
    void shouldRejectInvalidUrl() {
        DemoRequest request = new DemoRequest();
        request.mobile = "13800138000";
        request.url = "not-a-url";
        request.fileName = "avatar.jpg";
        request.status = 1;
        request.statusList = List.of(1);

        Set<ConstraintViolation<DemoRequest>> violations = VALIDATOR.validate(request);
        assertTrue(containsProperty(violations, "url"));
    }

    @Test
    void shouldValidateFileTypeIgnoreCase() {
        DemoRequest request = new DemoRequest();
        request.mobile = "13800138000";
        request.url = "https://example.com";
        request.fileName = "avatar.PnG";
        request.status = 1;
        request.statusList = List.of(1);

        Set<ConstraintViolation<DemoRequest>> violations = VALIDATOR.validate(request);
        assertFalse(containsProperty(violations, "fileName"));

        request.fileName = "archive.zip";
        violations = VALIDATOR.validate(request);
        assertTrue(containsProperty(violations, "fileName"));
    }

    @Test
    void shouldRejectInvalidEnumValue() {
        DemoRequest request = new DemoRequest();
        request.mobile = "13800138000";
        request.url = "https://example.com";
        request.fileName = "avatar.jpg";
        request.status = 3;
        request.statusList = List.of(1);

        Set<ConstraintViolation<DemoRequest>> violations = VALIDATOR.validate(request);
        assertTrue(containsProperty(violations, "status"));
    }

    @Test
    void shouldRejectInvalidEnumCollection() {
        DemoRequest request = new DemoRequest();
        request.mobile = "13800138000";
        request.url = "https://example.com";
        request.fileName = "avatar.jpg";
        request.status = 1;
        request.statusList = List.of(1, 3, null);

        Set<ConstraintViolation<DemoRequest>> violations = VALIDATOR.validate(request);
        assertTrue(containsProperty(violations, "statusList"));
        assertEquals(1, violations.stream()
                .filter(v -> "statusList".equals(v.getPropertyPath().toString()))
                .count());
    }

    private boolean containsProperty(Set<? extends ConstraintViolation<?>> violations, String property) {
        return violations.stream().anyMatch(v -> property.equals(v.getPropertyPath().toString()));
    }

    @SuppressWarnings("unused")
    private static class DemoRequest {

        @Mobile
        private String mobile;

        @Url(required = false)
        private String url;

        @FileType(value = {"jpg", "png"})
        private String fileName;

        @InEnum(enumClass = StatusEnum.class)
        private Integer status;

        @InEnum(enumClass = StatusEnum.class, allowNull = false, allowEmpty = false)
        private List<Integer> statusList;
    }

    private enum StatusEnum implements Valuable<Integer> {
        ENABLED(1),
        DISABLED(2);

        private final Integer value;

        StatusEnum(Integer value) {
            this.value = value;
        }

        @Override
        public Integer getValue() {
            return value;
        }
    }
}
