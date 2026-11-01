package com.zp.gmall.module.promotion.convert.banner;

import com.zp.gmall.module.promotion.controller.admin.banner.dto.BannerDTO;
import com.zp.gmall.module.promotion.controller.admin.banner.vo.BannerVO;
import com.zp.gmall.module.promotion.entity.banner.BannerDO;
import org.mapstruct.Mapper;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 01:52
 * Version : v1.0.0
 * Description:
 */
@Mapper
public interface BannerConvert {
    BannerDO convert(BannerDTO dto);

    BannerVO convert(BannerDO doo);
}
