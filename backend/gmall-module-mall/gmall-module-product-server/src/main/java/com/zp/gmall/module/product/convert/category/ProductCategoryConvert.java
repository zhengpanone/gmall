package com.zp.gmall.module.product.convert.category;

import com.zp.gmall.module.product.controller.admin.category.dto.ProductCategoryDTO;
import com.zp.gmall.module.product.controller.admin.category.vo.ProductCategoryVO;
import com.zp.gmall.module.product.entity.category.ProductCategoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * Description: 商品分类转换器
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-12
 */
@Mapper(componentModel = "spring")
public interface ProductCategoryConvert {

    ProductCategoryVO convert(ProductCategoryDO categoryDO);

    @Mapping(target = "level", ignore = true)
    ProductCategoryDO convert(ProductCategoryDTO categoryDTO);
}
