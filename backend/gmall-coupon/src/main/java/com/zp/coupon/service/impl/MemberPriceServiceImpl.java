package com.zp.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zp.common.service.impl.CrudServiceImpl;
import com.zp.coupon.dao.MemberPriceDao;
import com.zp.coupon.dto.MemberPriceDTO;
import com.zp.coupon.entity.MemberPriceEntity;
import com.zp.coupon.service.MemberPriceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品会员价格
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Service
public class MemberPriceServiceImpl extends CrudServiceImpl<MemberPriceDao, MemberPriceEntity, MemberPriceDTO> implements MemberPriceService {

    @Override
    public QueryWrapper<MemberPriceEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MemberPriceEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}