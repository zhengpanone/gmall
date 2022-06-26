package com.zp.product.dao;

import com.zp.common.dao.BaseDao;
import com.zp.product.entity.BrandEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Mapper
public interface BrandDao extends BaseDao<BrandEntity> {
	
}