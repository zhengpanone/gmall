package com.zp.coupon.dao;

import com.zp.common.dao.BaseDao;
import com.zp.coupon.entity.CouponSpuCategoryRelationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券分类关联
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Mapper
public interface CouponSpuCategoryRelationDao extends BaseDao<CouponSpuCategoryRelationEntity> {
	
}