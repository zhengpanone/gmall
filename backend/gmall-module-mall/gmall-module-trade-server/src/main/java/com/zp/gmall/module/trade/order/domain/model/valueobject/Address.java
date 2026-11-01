package com.zp.gmall.module.trade.order.domain.model.valueobject;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Data
public final class Address {
    private final String province;
    private final String city;
    private final String region;
    private final String detail;
    private final String phone;

    public Address(String province, String city, String region, String detail, String phone) {
        if (StringUtils.isBlank(province)) {
            throw new IllegalArgumentException("省份不能为空");
        }
        if (StringUtils.isBlank(city)) {
            throw new IllegalArgumentException("城市不能为空");
        }
        if (StringUtils.isBlank(region)) {
            throw new IllegalArgumentException("区/县不能为空");
        }
        if (StringUtils.isBlank(detail)) {
            throw new IllegalArgumentException("详细地址不能为空");
        }
        if (!isPhone(phone)) {
            throw new IllegalArgumentException("手机号非法");
        }
        this.province = province;
        this.city = city;
        this.region = region;
        this.detail = detail;
        this.phone = phone;
    }

    private boolean isPhone(String phone) {

        return phone != null
                && phone.matches("^1[3-9]\\d{9}$");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address other)) {
            return false;
        }


        return Objects.equals(
                province,
                other.province)
                && Objects.equals(
                city,
                other.city)
                && Objects.equals(
                detail,
                other.detail)
                && Objects.equals(
                phone,
                other.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                province,
                city,
                detail,
                phone
        );
    }
}
