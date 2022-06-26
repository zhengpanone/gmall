package com.zp.product.dao;

import com.zp.common.dao.BaseDao;
import com.zp.product.entity.SpuCommentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Mapper
public interface SpuCommentDao extends BaseDao<SpuCommentEntity> {
	
}