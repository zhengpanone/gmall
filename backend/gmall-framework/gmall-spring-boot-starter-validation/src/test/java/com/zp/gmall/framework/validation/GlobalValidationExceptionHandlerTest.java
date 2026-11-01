package com.zp.gmall.framework.validation;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.framework.validation.exception.ValidationException;
import com.zp.gmall.framework.validation.handler.GlobalValidationExceptionHandler;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.Set;

import static com.zp.gmall.framework.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GlobalValidationExceptionHandlerTest {

    private static final Validator VALIDATOR =
            Validation.buildDefaultValidatorFactory().getValidator();

    private final GlobalValidationExceptionHandler handler = new GlobalValidationExceptionHandler();

    @Test
    void shouldHandleValidationException() {
        ValidationException exception = new ValidationException(400, "参数错误");
        Result<?> result = handler.validationException(exception);
        assertEquals(400, result.getCode());
        assertEquals("参数错误", result.getMsg());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldHandleConstraintViolationException() {
        Set<ConstraintViolation<DemoParam>> violations =
                VALIDATOR.validateValue(DemoParam.class, "name", "");
        ConstraintViolationException exception =
                new ConstraintViolationException((Set<ConstraintViolation<?>>) (Set<?>) violations);

        Result<?> result = handler.constraintViolationException(exception);
        assertEquals(BAD_REQUEST.getCode(), result.getCode());
        assertTrue(result.getMsg().contains("请求参数不正确"));
    }

    @Test
    void shouldHandleBindException() {
        DemoParam demo = new DemoParam();
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(demo, "demoParam");
        bindingResult.addError(new FieldError("demoParam", "name", "名称不能为空"));
        BindException exception = new BindException(bindingResult);

        Result<?> result = handler.bindException(exception);
        assertEquals(BAD_REQUEST.getCode(), result.getCode());
        assertEquals("请求参数不正确:名称不能为空", result.getMsg());
    }

    private static class DemoParam {
        @NotBlank(message = "名称不能为空")
        private String name;
    }
}
