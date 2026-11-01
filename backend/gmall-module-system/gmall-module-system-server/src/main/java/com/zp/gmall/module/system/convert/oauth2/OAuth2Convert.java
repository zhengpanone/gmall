package com.zp.gmall.module.system.convert.oauth2;

import com.zp.gmall.framework.common.biz.oauth2.vo.OAuth2AccessTokenCheckVO;
import com.zp.gmall.framework.common.biz.oauth2.vo.OAuth2AccessTokenVO;
import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;
import com.zp.gmall.module.system.entity.oauth2.OAuth2RefreshTokenDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-15
 */
@Mapper(componentModel = "spring")
public interface OAuth2Convert {

    OAuth2Convert INSTANCE = Mappers.getMapper(OAuth2Convert.class);

    OAuth2AccessTokenVO convert(OAuth2AccessTokenDO oAuth2AccessTokenDO);

    OAuth2AccessTokenCheckVO convert2CheckVO(OAuth2AccessTokenDO oAuth2AccessTokenDO);

    OAuth2AccessTokenDO convert(OAuth2RefreshTokenDO oAuth2RefreshTokenDO);
}
