package com.zp.product.dao;

import com.zp.common.dao.BaseDao;
import com.zp.product.entity.SpuInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * spu信息
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Mapper
public interface SpuInfoDao extends BaseDao<SpuInfoEntity> {
	
}