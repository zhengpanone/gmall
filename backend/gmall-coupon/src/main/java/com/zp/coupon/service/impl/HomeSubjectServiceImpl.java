package com.zp.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zp.common.service.impl.CrudServiceImpl;
import com.zp.coupon.dao.HomeSubjectDao;
import com.zp.coupon.dto.HomeSubjectDTO;
import com.zp.coupon.entity.HomeSubjectEntity;
import com.zp.coupon.service.HomeSubjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Service
public class HomeSubjectServiceImpl extends CrudServiceImpl<HomeSubjectDao, HomeSubjectEntity, HomeSubjectDTO> implements HomeSubjectService {

    @Override
    public QueryWrapper<HomeSubjectEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<HomeSubjectEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}