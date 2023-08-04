package com.zp.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zp.common.service.impl.CrudServiceImpl;
import com.zp.product.dao.CategoryDao;
import com.zp.product.dto.CategoryDTO;
import com.zp.product.entity.CategoryEntity;
import com.zp.product.mapstruct.struct.CategoryConvert;
import com.zp.product.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品三级分类
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Service
public class CategoryServiceImpl extends CrudServiceImpl<CategoryDao, CategoryEntity, CategoryDTO> implements CategoryService {

    @Autowired
    private CategoryConvert categoryConvert;

    @Override
    public QueryWrapper<CategoryEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<CategoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public List<CategoryEntity> listTree() {
        // 查出所有分类
        Optional<List<CategoryEntity>> optional = Optional.ofNullable(baseDao.selectList(null));
        if (optional.isPresent()) {
            List<CategoryEntity> level1 = optional.orElseGet(Collections::emptyList).stream()
                    .filter(categoryEntity -> categoryEntity.getParentCid() == 0L)
                    .map(item -> {
                        item.setChildren(getChildrens(item, optional.get()));
                        return item;
                    })
                    .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                    .collect(Collectors.toList());
            return level1;
        }
        return Collections.emptyList();
    }


    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        return all.stream().filter(categoryEntity -> {
            return root.getCatId() == categoryEntity.getParentCid();
        }).map(item -> {
            item.setChildren(getChildrens(item, all));
            return item;
        }).sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort()))).collect(Collectors.toList());
    }
}