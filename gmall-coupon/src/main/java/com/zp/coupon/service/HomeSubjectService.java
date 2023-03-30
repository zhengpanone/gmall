package com.zp.coupon.service;

import com.zp.common.service.CrudService;
import com.zp.coupon.dto.HomeSubjectDTO;
import com.zp.coupon.entity.HomeSubjectEntity;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
public interface HomeSubjectService extends CrudService<HomeSubjectEntity, HomeSubjectDTO> {

}