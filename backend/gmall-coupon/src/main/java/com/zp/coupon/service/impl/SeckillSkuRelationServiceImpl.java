package com.zp.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zp.common.service.impl.CrudServiceImpl;
import com.zp.coupon.dao.SeckillSkuRelationDao;
import com.zp.coupon.dto.SeckillSkuRelationDTO;
import com.zp.coupon.entity.SeckillSkuRelationEntity;
import com.zp.coupon.service.SeckillSkuRelationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 秒杀活动商品关联
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Service
public class SeckillSkuRelationServiceImpl extends CrudServiceImpl<SeckillSkuRelationDao, SeckillSkuRelationEntity, SeckillSkuRelationDTO> implements SeckillSkuRelationService {

    @Override
    public QueryWrapper<SeckillSkuRelationEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SeckillSkuRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}