package com.zp.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品阶梯价格
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sms_sku_ladder")
public class SkuLadderEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * spu_id
     */
	private Long skuId;
    /**
     * 满几件
     */
	private Integer fullCount;
    /**
     * 打几折
     */
	private BigDecimal discount;
    /**
     * 折后价
     */
	private BigDecimal price;
    /**
     * 是否叠加其他优惠[0-不可叠加，1-可叠加]
     */
	private Integer addOther;
}