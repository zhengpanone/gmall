package com.zp.coupon.dao;

import com.zp.common.dao.BaseDao;
import com.zp.coupon.entity.MemberPriceEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品会员价格
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Mapper
public interface MemberPriceDao extends BaseDao<MemberPriceEntity> {
	
}