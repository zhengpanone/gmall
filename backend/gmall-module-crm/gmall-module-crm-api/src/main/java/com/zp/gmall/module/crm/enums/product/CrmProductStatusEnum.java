package com.zp.gmall.module.crm.enums.product;

import com.zp.gmall.framework.common.core.Valuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:03
 * Version : v1.0.0
 * Description:
 */
@Getter
@AllArgsConstructor
public enum CrmProductStatusEnum implements Valuable<String> {
    DISABLE("0", "上架"),
    ENABLE("1", "下架"),
    ;

    private final String status;
    private final String name;

    @Override
    public String getValue() {
        return status;
    }

    public static boolean isEnable(String status) {
        return ENABLE.status.equals(status);
    }

    public static boolean isDisable(String status) {
        return DISABLE.status.equals(status);
    }
}
