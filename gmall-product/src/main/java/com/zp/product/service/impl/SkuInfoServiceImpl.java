package com.zp.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zp.common.service.impl.CrudServiceImpl;
import com.zp.product.dao.SkuInfoDao;
import com.zp.product.dto.SkuInfoDTO;
import com.zp.product.entity.SkuInfoEntity;
import com.zp.product.service.SkuInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * sku信息
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Service
public class SkuInfoServiceImpl extends CrudServiceImpl<SkuInfoDao, SkuInfoEntity, SkuInfoDTO> implements SkuInfoService {

    @Override
    public QueryWrapper<SkuInfoEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SkuInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}