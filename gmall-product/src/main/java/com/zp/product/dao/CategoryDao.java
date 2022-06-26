package com.zp.product.dao;

import com.zp.common.dao.BaseDao;
import com.zp.product.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Mapper
public interface CategoryDao extends BaseDao<CategoryEntity> {
	
}