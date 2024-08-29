package com.zp.module.system.service.oauth2.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.zp.framework.common.enums.UserTypeEnum;
import com.zp.framework.common.pojo.PageResult;
import com.zp.framework.security.core.LoginUser;
import com.zp.framework.tenant.core.context.TenantContextHolder;
import com.zp.module.system.controller.admin.oauth2.vo.token.OAuth2AccessTokenPageReqVO;
import com.zp.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import com.zp.module.system.dal.dataobject.oauth2.OAuth2ClientDO;
import com.zp.module.system.dal.dataobject.oauth2.OAuth2RefreshTokenDO;
import com.zp.module.system.dal.dataobject.user.AdminUserDO;
import com.zp.module.system.dao.oauth2.OAuth2AccessTokenMapper;
import com.zp.module.system.dao.oauth2.OAuth2RefreshTokenMapper;
import com.zp.module.system.repository.oauth2.OAuth2AccessTokenRedisRepository;
import com.zp.module.system.service.oauth2.OAuth2ClientService;
import com.zp.module.system.service.oauth2.OAuth2TokenService;
import com.zp.module.system.service.user.AdminUserService;
import com.zp.module.system.service.user.impl.AdminUserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Author : zhengpanone
 * Date : 2024/7/30 12:51
 * Version : v1.0.0
 * Description: OAuth2.0 Token Service 实现类
 */
@Service
public class OAuth2TokenServiceImpl implements OAuth2TokenService {
    @Resource
    private OAuth2ClientService oauth2ClientService;

    @Resource
    private OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;
    @Autowired
    private OAuth2AccessTokenRedisRepository oauth2AccessTokenRedisRepository;

    @Override
    public OAuth2AccessTokenDO createAccessToken(String userId, Integer userType, String clientId, List<String> scopes) {
        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(clientId);
        // 创建刷新令牌
        OAuth2RefreshTokenDO refreshTokenDO = createOAuth2RefreshToken(userId, userType, clientDO, scopes);
        return createOAuth2AccessToken(refreshTokenDO, clientDO);
    }

    @Override
    public OAuth2AccessTokenDO refreshAccessToken(String refreshToken, String clientId) {
        return null;
    }

    @Override
    public OAuth2AccessTokenDO getAccessToken(String accessToken) {
        return null;
    }

    @Override
    public OAuth2AccessTokenDO checkAccessToken(String accessToken) {
        return null;
    }

    @Override
    public OAuth2AccessTokenDO removeAccessToken(String accessToken) {
        return null;
    }

    @Override
    public PageResult<OAuth2AccessTokenDO> getAccessTokenPage(OAuth2AccessTokenPageReqVO reqVO) {
        return null;
    }

    private OAuth2AccessTokenDO createOAuth2AccessToken(OAuth2RefreshTokenDO refreshTokenDO, OAuth2ClientDO clientDO) {
        OAuth2AccessTokenDO accessTokenDO = new OAuth2AccessTokenDO().setAccessToken(generateRefreshToken())
                .setUserId(refreshTokenDO.getUserId()).setUserType(refreshTokenDO.getUserType())
                .setUserInfo(buildUserInfo(refreshTokenDO.getUserId(), refreshTokenDO.getUserType()))
                .setClientId(clientDO.getClientId()).setScopes(clientDO.getScopes())
                .setRefreshToken(refreshTokenDO.getRefreshToken())
                .setExpiresTime(LocalDateTime.now().plusSeconds(clientDO.getAccessTokenValiditySeconds()));
        // 手动设置租户编号，避免缓存到 Redis 的时候，无对应的租户编号
        accessTokenDO.setTenantId(TenantContextHolder.getTenantId());
        oauth2AccessTokenMapper.insert(accessTokenDO);
        // 记录到redis中
        oauth2AccessTokenRedisRepository.set(accessTokenDO);
        return accessTokenDO;
    }

    private OAuth2RefreshTokenDO createOAuth2RefreshToken(String userId, Integer userType, OAuth2ClientDO clientDO, List<String> scopes) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO().setRefreshToken(generateRefreshToken())
                .setUserId(userId)
                .setClientId(clientDO.getClientId()).setScopes(scopes)
                .setExpiresTime(LocalDateTime.now().plusSeconds(clientDO.getRefreshTokenValiditySeconds()));
        oauth2RefreshTokenMapper.insert(refreshToken);
        return refreshToken;
    }

    private Map<String, String> buildUserInfo(String userId, Integer userType) {
        if (UserTypeEnum.ADMIN.getValue().equals(userType)) {
            AdminUserDO user = adminUserService.getUser(userId);
            return MapUtil.builder(LoginUser.INFO_KEY_NICKNAME, user.getNickname())
                    .put(LoginUser.INFO_KEY_DEPT_ID, StrUtil.toStringOrNull(user.getDeptId()))
                    .build();
        } else if (UserTypeEnum.MEMBER.getValue().equals(userType)) {
            return Collections.emptyMap();
        }
        return Collections.emptyMap();
    }

    private static String generateRefreshToken() {
        return IdUtil.fastSimpleUUID();
    }
}
