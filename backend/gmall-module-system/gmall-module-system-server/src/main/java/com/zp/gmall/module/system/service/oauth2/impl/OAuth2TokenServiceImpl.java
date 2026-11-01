package com.zp.gmall.module.system.service.oauth2.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zp.gmall.framework.common.biz.oauth2.dto.OAuth2AccessTokenPageDTO;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.enums.UserTypeEnum;
import com.zp.gmall.framework.common.exception.enums.GlobalErrorCodeConstants;
import com.zp.gmall.framework.common.util.date.DateUtils;
import com.zp.gmall.framework.security.core.LoginUser;
import com.zp.gmall.framework.tenant.core.context.TenantContextHolder;
import com.zp.gmall.module.system.convert.oauth2.OAuth2Convert;
import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;
import com.zp.gmall.module.system.entity.oauth2.OAuth2ClientDO;
import com.zp.gmall.module.system.entity.oauth2.OAuth2RefreshTokenDO;
import com.zp.gmall.module.system.entity.user.UserDO;
import com.zp.gmall.module.system.mapper.oauth2.OAuth2AccessTokenMapper;
import com.zp.gmall.module.system.mapper.oauth2.OAuth2AccessTokenRedisDao;
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

import static com.zp.gmall.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.gmall.framework.common.util.collection.CollectionUtils.convertSet;

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

    private final OAuth2AccessTokenRedisDao oAuth2AccessTokenRedisDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OAuth2AccessTokenDO createAccessToken(String userId, String userType, String clientId, List<String> scopes) {
        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(clientId);
        OAuth2RefreshTokenDO refreshToken = createOAuth2RefreshToken(userId, userType, clientDO, scopes);
        return createOAuth2AccessToken(refreshToken, clientDO);
    }

    @Override
    public OAuth2AccessTokenDO refreshAccessToken(String refreshToken, String clientId) {
        OAuth2RefreshTokenDO refreshTokenDO = oauth2RefreshTokenMapper.selectByRefreshToken(refreshToken);
        if (refreshTokenDO == null) {
            throw exception(GlobalErrorCodeConstants.BAD_REQUEST.getCode(), "无效的刷新令牌");
        }
        // 校验 Client 匹配
        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(clientId);
        if (ObjectUtil.notEqual(clientId, refreshTokenDO.getClientId())) {
            throw exception(GlobalErrorCodeConstants.BAD_REQUEST.getCode(), "刷新令牌的客户端编号不正确");
        }
        List<OAuth2AccessTokenDO> accessTokenDOS = oauth2AccessTokenMapper.selectListByRefreshToken(refreshToken);
        if (CollUtil.isNotEmpty(accessTokenDOS)) {
            oauth2AccessTokenMapper.deleteByIds(convertSet(accessTokenDOS, OAuth2AccessTokenDO::getId));
            oAuth2AccessTokenRedisDao.deleteList(convertSet(accessTokenDOS, OAuth2AccessTokenDO::getAccessToken));
        }
        // 已过期的情况下，删除刷新令牌
        if (DateUtils.isExpired(refreshTokenDO.getExpireTime())) {
            oauth2RefreshTokenMapper.deleteById(refreshTokenDO.getId());
            throw exception(GlobalErrorCodeConstants.UNAUTHORIZED.getCode(), "刷新令牌已过期");
        }
        return createOAuth2AccessToken(refreshTokenDO, clientDO);
    }

    @Override
    public OAuth2AccessTokenDO getAccessToken(String accessToken) {
        OAuth2AccessTokenDO accessTokenDO = oAuth2AccessTokenRedisDao.get(accessToken);
        if (accessTokenDO != null) {
            return accessTokenDO;
        }
        accessTokenDO = oauth2AccessTokenMapper.selectByAccessToken(accessToken);
        if (accessTokenDO == null) {
            // 特殊：从 MySQL 中获取刷新令牌。原因：解决部分场景不方便刷新访问令牌场景
            // 例如说，积木报表只允许传递 token，不允许传递 refresh_token，导致无法刷新访问令牌
            // 再例如说，前端 WebSocket 的 token 直接跟在 url 上，无法传递 refresh_token
            OAuth2RefreshTokenDO refreshTokenDO = oauth2RefreshTokenMapper.selectByRefreshToken(accessToken);
            if (refreshTokenDO != null && !DateUtils.isExpired(refreshTokenDO.getExpireTime())) {
                accessTokenDO = convertToAccessToken(refreshTokenDO);
            }
        }
        // 如果在 MySQL 存在，则往 Redis 中写入
        if (accessTokenDO != null && !DateUtils.isExpired(accessTokenDO.getExpiresTime())) {
            oAuth2AccessTokenRedisDao.set(accessTokenDO);
        }
        return accessTokenDO;
    }

    @Override
    public OAuth2AccessTokenDO checkAccessToken(String accessToken) {
        OAuth2AccessTokenDO accessTokenDO = getAccessToken(accessToken);
        if (accessTokenDO == null) {
            throw exception(GlobalErrorCodeConstants.UNAUTHORIZED.getCode(), "访问令牌不存在");
        }
        if (DateUtils.isExpired(accessTokenDO.getExpiresTime())) {
            throw exception(GlobalErrorCodeConstants.UNAUTHORIZED.getCode(), "访问令牌已过期");
        }
        return accessTokenDO;
    }

    @Override
    public OAuth2AccessTokenDO removeAccessToken(String accessToken) {
        // 删除访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2AccessTokenMapper.selectByAccessToken(accessToken);
        if (accessTokenDO == null) {
            return null;
        }
        oauth2AccessTokenMapper.deleteById(accessTokenDO.getId());
        oAuth2AccessTokenRedisDao.delete(accessToken);

        // 删除刷新令牌
        oauth2RefreshTokenMapper.deleteByRefreshToken(accessTokenDO.getRefreshToken());
        oAuth2AccessTokenRedisDao.delete(accessTokenDO.getRefreshToken());
        return accessTokenDO;
    }

    @Override
    public void removeAccessToken(String userId, String userType) {
        List<OAuth2AccessTokenDO> accessTokens = oauth2AccessTokenMapper.selectListByUserIdAndUserType(userId, userType);
        if (CollUtil.isEmpty(accessTokens)) {
            return;
        }
        accessTokens.forEach(accessToken -> {
            // 删除访问令牌
            oauth2AccessTokenMapper.deleteById(accessToken.getId());
            oAuth2AccessTokenRedisDao.delete(accessToken.getAccessToken());
            // 删除刷新令牌
            oauth2RefreshTokenMapper.deleteByRefreshToken(accessToken.getRefreshToken());
            oAuth2AccessTokenRedisDao.delete(accessToken.getRefreshToken());
        });
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
        if (tenantId == null) {
            tenantId = TenantContextHolder.getTenantId();
        }
        accessToken.setTenantId(tenantId);
        oauth2AccessTokenMapper.insert(accessToken);
        // 记录到缓存
        oAuth2AccessTokenRedisDao.set(accessToken);
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


    private OAuth2AccessTokenDO convertToAccessToken(OAuth2RefreshTokenDO refreshTokenDO) {
        OAuth2AccessTokenDO accessTokenDO = OAuth2Convert.INSTANCE.convert(refreshTokenDO);
        accessTokenDO.setAccessToken(refreshTokenDO.getRefreshToken());
        return accessTokenDO;
    }
}
