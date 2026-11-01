package com.zp.gmall.module.promotion.service.banner.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.promotion.entity.banner.BannerDO;
import com.zp.gmall.module.promotion.mapper.banner.BannerMapper;
import com.zp.gmall.module.promotion.service.banner.IBannerService;
import org.springframework.stereotype.Service;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 01:49
 * Version : v1.0.0
 * Description:
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, BannerDO> implements IBannerService {
}
