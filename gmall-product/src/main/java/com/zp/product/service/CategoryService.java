package com.zp.product.service;

import com.zp.common.service.CrudService;
import com.zp.product.dto.CategoryDTO;
import com.zp.product.entity.CategoryEntity;
import com.zp.product.vo.CategoryTreeVO;
import com.zp.product.vo.CategoryVO;

import java.util.List;

/**
 * 商品三级分类
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
public interface CategoryService extends CrudService<CategoryEntity, CategoryDTO> {

    List<CategoryEntity> listTree();
}