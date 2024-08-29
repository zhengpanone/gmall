package com.zp.module.system.dao.oauth2;

import com.zp.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author : zhengpanone
 * Date : 2024/8/6 14:05
 * Version : v1.0.0
 * Description:
 */
@Mapper
public interface OAuth2AccessTokenMapper extends BaseMapperX<OAuth2AccessTokenDO> {
}
