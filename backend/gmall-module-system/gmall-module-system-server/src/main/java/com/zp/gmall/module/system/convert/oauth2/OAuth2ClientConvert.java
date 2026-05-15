package com.zp.gmall.module.system.convert.oauth2;

import com.zp.gmall.module.system.controller.admin.oauth2.dto.OAuth2ClientDTO;
import com.zp.gmall.module.system.controller.admin.oauth2.vo.OAuth2ClientVO;
import com.zp.gmall.module.system.entity.oauth2.OAuth2ClientDO;
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
public interface OAuth2ClientConvert {

    OAuth2ClientVO convert(OAuth2ClientDO oAuth2ClientDO);

    OAuth2ClientDO convert(OAuth2ClientDTO oAuth2ClientDTO);
}
