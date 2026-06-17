package com.zp.gmall.module.system.mapper.oauth2;

import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.module.system.entity.oauth2.OAuth2AccessTokenDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-15
 */
@Mapper
public interface OAuth2AccessTokenMapper extends BaseMapperX<OAuth2AccessTokenDO> {

    default OAuth2AccessTokenDO selectByAccessToken(String accessToken) {
        return selectOne(OAuth2AccessTokenDO::getAccessToken, accessToken);
    }

    default List<OAuth2AccessTokenDO> selectListByUserIdAndUserType(String userId, String userType) {
        return selectList(OAuth2AccessTokenDO::getUserId, userId, OAuth2AccessTokenDO::getUserType, userType);
    }

    default List<OAuth2AccessTokenDO> selectListByRefreshToken(String refreshToken) {
        return selectList(OAuth2AccessTokenDO::getRefreshToken, refreshToken);
    }
}
