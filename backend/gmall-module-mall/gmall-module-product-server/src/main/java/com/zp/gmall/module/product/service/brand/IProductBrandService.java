package com.zp.gmall.module.product.service.brand;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.product.controller.admin.brand.dto.ProductBrandDTO;
import com.zp.gmall.module.product.controller.admin.brand.dto.ProductBrandPageDTO;
import com.zp.gmall.module.product.controller.admin.brand.vo.ProductBrandVO;
import com.zp.gmall.module.product.entity.brand.ProductBrandDO;
import jakarta.validation.Valid;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
public interface IProductBrandService extends IService<ProductBrandDO> {
    ProductBrandVO getBrandById(String id);

    PageResult<ProductBrandVO> getBrandPage(@Valid ProductBrandPageDTO productBrandPageDTO);

    void deleteBrand(@Valid Ids ids);

    void updateBrand(@Valid ProductBrandDTO productBrandDTO);

    void createBrand(@Valid ProductBrandDTO productBrandDTO);
}
