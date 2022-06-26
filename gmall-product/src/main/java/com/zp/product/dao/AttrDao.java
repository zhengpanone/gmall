package com.zp.product.dao;

import com.zp.common.dao.BaseDao;
import com.zp.product.entity.AttrEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品属性
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Mapper
public interface AttrDao extends BaseDao<AttrEntity> {
	
}