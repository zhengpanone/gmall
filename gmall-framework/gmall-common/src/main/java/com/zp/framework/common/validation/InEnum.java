package com.zp.framework.common.validation;

import com.zp.framework.common.core.IntArrayValuable;

import java.lang.annotation.Documented;

/**
 * Author : zhengpanone
 * Date : 2023/12/20 19:44
 * Version : v1.0.0
 
 */
@Documented
public @interface InEnum {
    /**
     * @return 实现EnumValuable接口的
     */
    Class<? extends IntArrayValuable> value();

}
