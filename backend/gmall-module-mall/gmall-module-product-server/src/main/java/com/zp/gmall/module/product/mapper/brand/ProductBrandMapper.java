package com.zp.gmall.module.product.mapper.brand;

import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.module.product.entity.brand.ProductBrandDO;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * Description: 商品品牌Mapper
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Mapper
public interface ProductBrandMapper extends BaseMapperX<ProductBrandDO> {

    ProductBrandDO selectByName(String name);
}
