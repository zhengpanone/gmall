package com.zp.framework.security.core.annotations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;

import java.lang.annotation.*;

/**
 * Author : zhengpanone
 * Date : 2024/1/10 14:16
 * Version : v1.0.0
 * Description:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited // 许子类继承父类中的注解
@Documented
public @interface PreAuthenticated {

}
