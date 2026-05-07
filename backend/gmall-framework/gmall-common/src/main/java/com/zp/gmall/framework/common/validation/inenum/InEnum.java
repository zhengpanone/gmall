package com.zp.gmall.framework.common.validation.inenum;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

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
@Constraint(
        validatedBy = {InEnumValidator.class, InEnumCollectionValidator.class}
)
public @interface InEnum {

    /**
     * 枚举类型
     */
    Class<? extends Enum<?>> enumClass();

    /**
     * 是否允许 null
     */
    boolean allowNull() default false;

    /**
     * 集合是否允许 empty
     */
    boolean allowEmpty() default true;

    /**
     * 是否忽略大小写
     */
    boolean ignoreCase() default false;

    /**
     * 错误消息
     */
    String message() default "参数值[{value}]不正确，可选值:[{allowed}]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
