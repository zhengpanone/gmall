package com.zp.gmall.module.product.service.brand.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.annotations.VisibleForTesting;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.product.controller.admin.brand.dto.ProductBrandDTO;
import com.zp.gmall.module.product.controller.admin.brand.dto.ProductBrandPageDTO;
import com.zp.gmall.module.product.controller.admin.brand.vo.ProductBrandVO;
import com.zp.gmall.module.product.convert.brand.ProductBrandConvert;
import com.zp.gmall.module.product.entity.brand.ProductBrandDO;
import com.zp.gmall.module.product.mapper.brand.ProductBrandMapper;
import com.zp.gmall.module.product.service.brand.IProductBrandService;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.zp.gmall.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.gmall.module.product.constant.ErrorCodeConstants.BRAND_NAME_EXISTS;
import static com.zp.gmall.module.product.constant.ErrorCodeConstants.BRAND_NOT_EXISTS;

/**
 *
 * Description: 产品品牌服务实现类
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Service
public class ProductBrandServiceImpl extends ServiceImpl<ProductBrandMapper, ProductBrandDO> implements IProductBrandService {

    private final ProductBrandConvert convertMapper = Mappers.getMapper(ProductBrandConvert.class);

    @Override
    public ProductBrandVO getById(String id) {
        ProductBrandDO productBrandDO = baseMapper.selectById(id);
        if (productBrandDO == null) {
            throw exception(BRAND_NOT_EXISTS);
        }
        return convertMapper.convert(productBrandDO);
    }

    @Override
    public PageResult<ProductBrandVO> getPage(ProductBrandPageDTO dto) {
        Page<ProductBrandDO> page = new Page<>(dto.getPageNo(), dto.getPageSize());
        LambdaQueryWrapper<ProductBrandDO> queryWrapper = Wrappers.<ProductBrandDO>lambdaQuery()
                .eq(StringUtils.isNotBlank(dto.getName()), ProductBrandDO::getName, dto.getName());
        Page<ProductBrandDO> brandPage = baseMapper.selectPage(page, queryWrapper);

        List<ProductBrandVO> voList = brandPage.getRecords().stream()
                .map(convertMapper::convert).collect(Collectors.toList());

        return PageResult.ok(brandPage.getTotal(), voList);
    }

    @Override
    public void delete(Ids ids) {
        baseMapper.deleteByIds(ids.getIds());
    }

    @Override
    public void update(ProductBrandDTO productBrandDTO) {
        // 校验存在
        validateBrandExists(productBrandDTO.getId());
        validateBrandNameUnique(productBrandDTO.getId(), productBrandDTO.getName());
        // 更新
        ProductBrandDO updateObj = convertMapper.convert(productBrandDTO);
        baseMapper.updateById(updateObj);
    }

    @Override
    public void create(ProductBrandDTO productBrandDTO) {
        // 校验
        validateBrandNameUnique(null, productBrandDTO.getName());
        // 插入
        ProductBrandDO brand = convertMapper.convert(productBrandDTO);
        baseMapper.insert(brand);

    }

    private void validateBrandExists(String id) {
        if (baseMapper.selectById(id) == null) {
            throw exception(BRAND_NOT_EXISTS);
        }
    }

    @VisibleForTesting
    public void validateBrandNameUnique(String id, String name) {
        ProductBrandDO brandDO = baseMapper.selectByName(name);
        if (brandDO == null) {
            return;
        }
        if (!brandDO.getId().equals(id)) {
            throw exception(BRAND_NAME_EXISTS);
        }
    }
}
