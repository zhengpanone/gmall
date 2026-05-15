package com.zp.gmall.module.system.convert.oauth2;

import com.zp.gmall.framework.common.biz.oauth2.vo.OAuth2AccessTokenVO;
import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;
import org.mapstruct.Mapper;

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

    OAuth2AccessTokenVO convert(OAuth2AccessTokenDO oAuth2AccessTokenDO);


}
