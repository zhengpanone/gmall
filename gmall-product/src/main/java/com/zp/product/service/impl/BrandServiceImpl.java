package com.zp.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zp.common.service.impl.CrudServiceImpl;
import com.zp.product.dao.BrandDao;
import com.zp.product.dto.BrandDTO;
import com.zp.product.entity.BrandEntity;
import com.zp.product.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 品牌
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Service
public class BrandServiceImpl extends CrudServiceImpl<BrandDao, BrandEntity, BrandDTO> implements BrandService {

    @Override
    public QueryWrapper<BrandEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}