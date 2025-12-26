package com.zp.module.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("member_discount_rule")
public class MemberDiscountRule {

    private String id;
    private String memberType;
    private BigDecimal discount;
    private Integer enabled;
    private Integer priority;

}
