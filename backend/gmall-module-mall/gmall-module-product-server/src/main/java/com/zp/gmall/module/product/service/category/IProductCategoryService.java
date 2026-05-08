package com.zp.gmall.module.product.service.category;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.product.controller.admin.category.dto.ProductCategoryDTO;
import com.zp.gmall.module.product.controller.admin.category.dto.ProductCategoryPageDTO;
import com.zp.gmall.module.product.controller.admin.category.vo.ProductCategoryVO;
import com.zp.gmall.module.product.entity.category.ProductCategoryDO;
import jakarta.validation.Valid;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
public interface IProductCategoryService extends IService<ProductCategoryDO> {
    void updateCategory(@Valid ProductCategoryDTO productCategoryDTO);

    void deleteCategory(@Valid Ids ids);

    void createCategory(@Valid ProductCategoryDTO productCategoryDTO);

    PageResult<ProductCategoryVO> getCategoryPage(@Valid ProductCategoryPageDTO productCategoryPageDTO);
}
