/*
package com.zp.common.annotation;

import com.zp.common.validate.MobileValidator;


import java.lang.annotation.*;

//自定义注解类
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MobileValidator.class)
public @interface Mobile {
    */
/**
     * 是否允许为空
     *//*

    boolean required() default true;

    */
/**
     * 校验不通过返回的提示信息
     *//*

    String message() default "不是一个手机号码格式";

    */
/**
     * Constraint要求的属性，用于分组校验和扩展，留空就好
     *//*

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
*/
