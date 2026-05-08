package com.zp.gmall.framework.validation.annotation.file;

import com.zp.gmall.framework.validation.validator.file.FileTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 文件后缀校验
 */
@Target({
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER,
        ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FileTypeValidator.class)
public @interface FileType {

    /**
     * 允许的后缀列表，例如 {"jpg", "png"}。
     */
    String[] value();

    String message() default "文件类型不正确，允许类型: {allowed}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 是否必填
     */
    boolean required() default true;

    /**
     * 后缀匹配是否忽略大小写
     */
    boolean ignoreCase() default true;
}
