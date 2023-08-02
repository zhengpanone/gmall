package com.zp.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zp.common.service.impl.CrudServiceImpl;
import com.zp.common.utils.TreeUtils;
import com.zp.product.dao.CategoryDao;
import com.zp.product.dto.CategoryDTO;
import com.zp.product.entity.CategoryEntity;
import com.zp.product.mapstruct.struct.CategoryConvert;
import com.zp.product.service.CategoryService;
import com.zp.product.vo.CategoryTreeVO;
import com.zp.product.vo.CategoryVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public QueryWrapper<CategoryEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CategoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public List<CategoryTreeVO> listTree() {
        // 查出所有分类
        List<CategoryEntity> allCategoryList = baseDao.selectList(null);
        List<CategoryTreeVO> vOs = categoryConvert.toTreeVOs(allCategoryList);
        // 组装树形结构
        return  TreeUtils.toTree(vOs);
    }
}