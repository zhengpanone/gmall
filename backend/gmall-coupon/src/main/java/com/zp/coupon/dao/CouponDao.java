package com.zp.coupon.dao;

import com.zp.common.dao.BaseDao;
import com.zp.coupon.entity.CouponEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Mapper
public interface CouponDao extends BaseDao<CouponEntity> {
	
}