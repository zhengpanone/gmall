package com.zp.gmall.module.product.service.category.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.product.controller.admin.category.dto.ProductCategoryDTO;
import com.zp.gmall.module.product.controller.admin.category.dto.ProductCategoryPageDTO;
import com.zp.gmall.module.product.controller.admin.category.vo.ProductCategoryVO;
import com.zp.gmall.module.product.entity.category.ProductCategoryDO;
import com.zp.gmall.module.product.mapper.category.ProductCategoryMapper;
import com.zp.gmall.module.product.service.category.IProductCategoryService;
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
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategoryDO> implements IProductCategoryService {
    @Override
    public void updateCategory(ProductCategoryDTO productCategoryDTO) {

    }

    @Override
    public void deleteCategory(Ids ids) {

    }

    @Override
    public void createCategory(ProductCategoryDTO productCategoryDTO) {

    }

    @Override
    public PageResult<ProductCategoryVO> getCategoryPage(ProductCategoryPageDTO productCategoryPageDTO) {
        return null;
    }
}
