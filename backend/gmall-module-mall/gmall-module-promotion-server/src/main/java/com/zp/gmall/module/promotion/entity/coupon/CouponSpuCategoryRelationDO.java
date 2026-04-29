package com.zp.gmall.module.promotion.entity.coupon;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 优惠券分类关联
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("promotion_coupon_spu_category_relation")
public class CouponSpuCategoryRelationDO extends BaseDO {
	private static final long serialVersionUID = 1L;

    /**
     * 优惠券id
     */
	private Long couponId;
    /**
     * 产品分类id
     */
	private Long categoryId;
    /**
     * 产品分类名称
     */
	private String categoryName;
}