package com.zp.gmall.module.product.convert.brand;

import com.zp.gmall.module.product.controller.admin.brand.dto.ProductBrandDTO;
import com.zp.gmall.module.product.controller.admin.brand.vo.ProductBrandVO;
import com.zp.gmall.module.product.entity.brand.ProductBrandDO;
import org.mapstruct.Mapper;

/**
 *
 * Description: 商品品牌转换器
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-12
 */
@Mapper(componentModel = "spring")
public interface ProductBrandConvert {

    ProductBrandVO convert(ProductBrandDO brandDO);

    ProductBrandDO convert(ProductBrandDTO brandDTO);
}
