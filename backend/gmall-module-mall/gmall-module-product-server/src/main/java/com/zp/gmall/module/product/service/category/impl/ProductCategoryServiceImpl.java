package com.zp.gmall.module.product.service.category.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.annotations.VisibleForTesting;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.module.product.controller.admin.category.dto.ProductCategoryDTO;
import com.zp.gmall.module.product.controller.admin.category.dto.ProductCategoryPageDTO;
import com.zp.gmall.module.product.controller.admin.category.vo.ProductCategoryVO;
import com.zp.gmall.module.product.convert.category.ProductCategoryConvert;
import com.zp.gmall.module.product.entity.category.ProductCategoryDO;
import com.zp.gmall.module.product.mapper.category.ProductCategoryMapper;
import com.zp.gmall.module.product.service.category.IProductCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.zp.gmall.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.gmall.module.product.constant.ErrorCodeConstants.*;

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

    private final ProductCategoryConvert convertMapper = Mappers.getMapper(ProductCategoryConvert.class);

    public static final String ROOT_ID = "0";

    @Override
    public void update(ProductCategoryDTO dto) {
        // 校验分类是否存在
        validateProductCategoryExists(dto.getId());
        // 校验父分类存在
        validateParentProductCategory(dto.getParentId());

        // 更新
        ProductCategoryDO updateObj = convertMapper.convert(dto);
        baseMapper.updateById(updateObj);

    }

    @Override
    public void delete(Ids ids) {
        baseMapper.deleteByIds(ids.getIds());
    }

    @Override
    public void create(ProductCategoryDTO dto) {
        // 校验父分类存在
        validateParentProductCategory(dto.getParentId());
        ProductCategoryDO category = convertMapper.convert(dto);
        baseMapper.insert(category);
    }

    @Override
    public PageResult<ProductCategoryVO> getPage(ProductCategoryPageDTO dto) {
        Page<ProductCategoryDO> page = new Page<>(dto.getPageNo(), dto.getPageSize());
        LambdaQueryWrapper<ProductCategoryDO> queryWrapper = Wrappers.<ProductCategoryDO>lambdaQuery()
                .like(StringUtils.isNotBlank(dto.getName()), ProductCategoryDO::getName, dto.getName());
        Page<ProductCategoryDO> categoryPage = baseMapper.selectPage(page, queryWrapper);

        List<ProductCategoryVO> voList = categoryPage.getRecords().stream()
                .map(convertMapper::convert).collect(Collectors.toList());
        return PageResult.ok(categoryPage.getTotal(), voList);
    }

    @Override
    public List<ProductCategoryVO> getList() {
        List<ProductCategoryDO> categoryList = baseMapper.selectList(Wrappers.<ProductCategoryDO>lambdaQuery()
                .eq(ProductCategoryDO::getStatus, CommonStatusEnum.ENABLE.getValue()));

        return categoryList.stream().map(convertMapper::convert).collect(Collectors.toList());
    }

    @Override
    public ProductCategoryVO getById(String id) {
        ProductCategoryDO categoryDO = baseMapper.selectById(id);
        if (categoryDO == null) {
            throw exception(CATEGORY_NOT_EXISTS);
        }
        return convertMapper.convert(categoryDO);
    }

    @VisibleForTesting
    private void validateParentProductCategory(String id) {
        // 根分类不校验
        if (Objects.equals(id, ROOT_ID)) {
            return;
        }
        // 父分类不存在
        ProductCategoryDO categoryDO = baseMapper.selectById(id);
        if (categoryDO == null) {
            throw exception(CATEGORY_PARENT_NOT_EXISTS);
        }
        if (!Objects.equals(categoryDO.getParentId(), ROOT_ID)) {
            throw exception(CATEGORY_PARENT_NOT_FIRST_LEVEL);
        }
    }

    private void validateProductCategoryExists(String id) {
        if (baseMapper.selectById(id) == null) {
            throw exception(CATEGORY_NOT_EXISTS);
        }
    }
}
