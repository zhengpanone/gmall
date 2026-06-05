package com.zp.gmall.module.system.service.oauth2.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.zp.gmall.framework.common.biz.oauth2.dto.OAuth2AccessTokenPageDTO;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.enums.UserTypeEnum;
import com.zp.gmall.framework.security.core.LoginUser;
import com.zp.gmall.framework.tenant.core.context.TenantContextHolder;
import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;
import com.zp.gmall.module.system.entity.oauth2.OAuth2ClientDO;
import com.zp.gmall.module.system.entity.oauth2.OAuth2RefreshTokenDO;
import com.zp.gmall.module.system.entity.user.UserDO;
import com.zp.gmall.module.system.mapper.oauth2.OAuth2AccessTokenMapper;
import com.zp.gmall.module.system.mapper.oauth2.OAuth2RefreshTokenMapper;
import com.zp.gmall.module.system.mapper.user.UserMapper;
import com.zp.gmall.module.system.service.oauth2.IOAuth2ClientService;
import com.zp.gmall.module.system.service.oauth2.IOAuth2TokenService;
import com.zp.gmall.module.system.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 19:03
 * Version : v1.0.0
 * Description:
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OAuth2TokenServiceImpl implements IOAuth2TokenService {

    private final OAuth2AccessTokenMapper oauth2AccessTokenMapper;

    private final OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;

    private final IOAuth2ClientService oauth2ClientService;

    private final UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OAuth2AccessTokenDO createAccessToken(String userId, String userType, String clientId, List<String> scopes) {
        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(clientId);
        OAuth2RefreshTokenDO refreshToken = createOAuth2RefreshToken(userId, userType, clientDO, scopes);
        return createOAuth2AccessToken(refreshToken, clientDO);
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
    public void removeAccessToken(Long userId, Integer userType) {

    }

    @Override
    public PageResult<OAuth2AccessTokenDO> getAccessTokenPage(OAuth2AccessTokenPageDTO dto) {
        return null;
    }

    @Override
    public Integer cleanRefreshToken(Integer exceedDay, Integer deleteLimit) {
        return 0;
    }

    @Override
    public Integer cleanAccessToken(Integer exceedDay, Integer deleteLimit) {
        return 0;
    }


    private OAuth2RefreshTokenDO createOAuth2RefreshToken(String userId, String userType, OAuth2ClientDO clientDO, List<String> scopes) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO().setRefreshToken(IdUtil.fastSimpleUUID())
                .setUserId(userId).setUserType(userType)
                .setClientId(clientDO.getClientId()).setScopes(scopes)
                .setExpireTime(LocalDateTime.now().plusSeconds(clientDO.getRefreshTokenValiditySeconds()));
        oauth2RefreshTokenMapper.insert(refreshToken);
        return refreshToken;
    }

    private OAuth2AccessTokenDO createOAuth2AccessToken(OAuth2RefreshTokenDO refreshToken, OAuth2ClientDO clientDO) {
        OAuth2AccessTokenDO accessToken = new OAuth2AccessTokenDO().setAccessToken(IdUtil.fastSimpleUUID())
                .setUserId(refreshToken.getUserId()).setUserType(refreshToken.getUserType())
                .setUserInfo(buildUserInfo(refreshToken.getUserId(), refreshToken.getUserType()))
                .setClientId(clientDO.getClientId()).setScopes(refreshToken.getScopes())
                .setRefreshToken(refreshToken.getRefreshToken())
                .setExpiresTime(LocalDateTime.now().plusSeconds(clientDO.getAccessTokenValiditySeconds()));

        String tenantId = refreshToken.getTenantId();
        if (tenantId != null) {
            tenantId = TenantContextHolder.getTenantId();
        }
        accessToken.setTenantId(tenantId);
        oauth2AccessTokenMapper.insert(accessToken);
        // 记录到缓存
        //oauth2AccessTokenCache.put(accessToken.getAccessToken(), accessToken);
        return accessToken;
    }

    /**
     * 加载用户信息，方便 {@link com.zp.gmall.framework.security.core.LoginUser} 获取到昵称、部门等信息
     *
     * @param userId   用户编号
     * @param userType 用户类型
     * @return 用户信息
     */
    private Map<String, String> buildUserInfo(String userId, String userType) {
        if (StrUtil.isBlank(userType)) {
            return Collections.emptyMap();
        }
        if (userType.equals(UserTypeEnum.ADMIN.getValue())) {
            UserDO user = userMapper.selectById(userId);
            return MapUtil.builder(LoginUser.INFO_KEY_NICKNAME, user.getNickname())
                    .put(LoginUser.INFO_KEY_DEPT_ID, StrUtil.toStringOrNull(user.getDeptId())).build();
        } else if (userType.equals(UserTypeEnum.MEMBER.getValue())) {
            // 注意：目前 Member 暂时不读取，可以按需实现
            return Collections.emptyMap();
        }
        throw new IllegalArgumentException("未知用户类型：" + userType);
    }
}
