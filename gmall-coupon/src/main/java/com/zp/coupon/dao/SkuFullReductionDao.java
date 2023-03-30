package com.zp.coupon.dao;

import com.zp.common.dao.BaseDao;
import com.zp.coupon.entity.SkuFullReductionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品满减信息
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Mapper
public interface SkuFullReductionDao extends BaseDao<SkuFullReductionEntity> {
	
}