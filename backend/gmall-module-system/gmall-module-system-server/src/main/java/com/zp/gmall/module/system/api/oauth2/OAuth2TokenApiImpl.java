package com.zp.gmall.module.system.api.oauth2;

import com.zp.gmall.framework.common.biz.oauth2.OAuth2TokenCommonApi;
import com.zp.gmall.framework.common.biz.oauth2.dto.OAuth2AccessTokenDTO;
import com.zp.gmall.framework.common.biz.oauth2.vo.OAuth2AccessTokenCheckVO;
import com.zp.gmall.framework.common.biz.oauth2.vo.OAuth2AccessTokenVO;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.system.convert.oauth2.OAuth2Convert;
import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;
import com.zp.gmall.module.system.service.oauth2.IOAuth2TokenService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-15
 */
@RequiredArgsConstructor
@RestController
@Validated
public class OAuth2TokenApiImpl implements OAuth2TokenCommonApi {

    private final IOAuth2TokenService oAuth2TokenService;

    private final OAuth2Convert convertMapper = Mappers.getMapper(OAuth2Convert.class);


    @Override
    public Result<OAuth2AccessTokenVO> createAccessToken(OAuth2AccessTokenDTO reqDTO) {
        OAuth2AccessTokenDO accessToken = oAuth2TokenService.createAccessToken(reqDTO.getUserId(), reqDTO.getUserType(), reqDTO.getClientId(), reqDTO.getScopes());
        OAuth2AccessTokenVO convert = convertMapper.convert(accessToken);
        return Result.ok(convert);
    }

    @Override
    public Result<OAuth2AccessTokenCheckVO> checkAccessToken(String accessToken) {
        OAuth2AccessTokenDO oAuth2AccessTokenDO = oAuth2TokenService.checkAccessToken(accessToken);
        return Result.ok(convertMapper.convert2CheckVO(oAuth2AccessTokenDO));
    }

    @Override
    public Result<OAuth2AccessTokenVO> removeAccessToken(String accessToken) {
        OAuth2AccessTokenDO accessTokenDO = oAuth2TokenService.removeAccessToken(accessToken);
        return Result.ok(convertMapper.convert(accessTokenDO));
    }

    @Override
    public Result<OAuth2AccessTokenVO> refreshAccessToken(String refreshToken, String clientId) {
        OAuth2AccessTokenDO accessTokenDO = oAuth2TokenService.refreshAccessToken(refreshToken, clientId);
        return Result.ok(convertMapper.convert(accessTokenDO));
    }
}
