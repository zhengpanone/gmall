package com.zp.coupon.dao;

import com.zp.common.dao.BaseDao;
import com.zp.coupon.entity.CouponHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Mapper
public interface CouponHistoryDao extends BaseDao<CouponHistoryEntity> {
	
}