package com.zp.gmall.module.product.service.brand.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.product.controller.admin.brand.dto.ProductBrandDTO;
import com.zp.gmall.module.product.controller.admin.brand.dto.ProductBrandPageDTO;
import com.zp.gmall.module.product.controller.admin.brand.vo.ProductBrandVO;
import com.zp.gmall.module.product.entity.brand.ProductBrandDO;
import com.zp.gmall.module.product.mapper.brand.ProductBrandMapper;
import com.zp.gmall.module.product.service.brand.IProductBrandService;
import org.springframework.stereotype.Service;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Service
public class ProductBrandServiceImpl extends ServiceImpl<ProductBrandMapper, ProductBrandDO> implements IProductBrandService {
    @Override
    public ProductBrandVO getBrandById(String id) {
        return null;
    }

    @Override
    public PageResult<ProductBrandVO> getBrandPage(ProductBrandPageDTO productBrandPageDTO) {
        return null;
    }

    @Override
    public void deleteBrand(Ids ids) {

    }

    @Override
    public void updateBrand(ProductBrandDTO productBrandDTO) {

    }

    @Override
    public void createBrand(ProductBrandDTO productBrandDTO) {

    }
}
