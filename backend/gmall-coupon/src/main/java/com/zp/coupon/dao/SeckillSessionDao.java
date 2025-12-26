package com.zp.coupon.dao;

import com.zp.common.dao.BaseDao;
import com.zp.coupon.entity.SeckillSessionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动场次
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Mapper
public interface SeckillSessionDao extends BaseDao<SeckillSessionEntity> {
	
}