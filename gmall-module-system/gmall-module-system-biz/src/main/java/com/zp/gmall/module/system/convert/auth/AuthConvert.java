package com.zp.gmall.module.system.convert.auth;

import com.zp.gmall.module.system.controller.admin.auth.vo.AuthLoginVO;
import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 19:15
 * Version : v1.0.0
 * Description:
 */
@Mapper
public interface AuthConvert {
    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    AuthLoginVO convert(OAuth2AccessTokenDO oAuth2AccessTokenDO);

}
