package com.zp.module.system.dao.oauth2;

import com.zp.framework.common.pojo.PageResult;
import com.zp.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zp.module.system.controller.admin.oauth2.dto.token.OAuth2ClientPageDTO;
import com.zp.module.system.dal.dataobject.oauth2.OAuth2ClientDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author : zhengpanone
 * Date : 2024/8/6 14:45
 * Version : v1.0.0
 * Description:
 */
@Mapper
public interface OAuth2ClientMapper extends BaseMapperX<OAuth2ClientDO> {


    default PageResult<OAuth2ClientDO> selectPage(OAuth2ClientPageDTO reqDTO) {
        return selectPage(reqDTO, new LambdaQueryWrapperX<OAuth2ClientDO>()
                .likeIfPresent(OAuth2ClientDO::getName, reqDTO.getName())
                .eqIfPresent(OAuth2ClientDO::getStatus, reqDTO.getStatus())
                .orderByDesc(OAuth2ClientDO::getUpdateTime)
        );
    }

    default OAuth2ClientDO selectByClientId(String clientId) {
        return selectOne(OAuth2ClientDO::getClientId, clientId);
    }
}
