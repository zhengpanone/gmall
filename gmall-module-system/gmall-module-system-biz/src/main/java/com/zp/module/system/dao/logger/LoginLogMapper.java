package com.zp.module.system.dao.logger;

import com.zp.framework.common.pojo.PageResult;
import com.zp.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zp.module.system.controller.admin.logger.dto.LoginLogPageDTO;
import com.zp.module.system.dal.dataobject.logger.LoginLogDO;
import com.zp.module.system.enums.logger.LoginResultEnum;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author : zhengpanone
 * Date : 2024/8/5 11:20
 * Version : v1.0.0
 * Description:
 */
@Mapper
public interface LoginLogMapper extends BaseMapperX<LoginLogDO> {

    default PageResult<LoginLogDO> selectPage(LoginLogPageDTO dto) {
        LambdaQueryWrapperX<LoginLogDO> query = new LambdaQueryWrapperX<LoginLogDO>()
                .likeIfPresent(LoginLogDO::getUserIp, dto.getUserIp())
                .likeIfPresent(LoginLogDO::getUsername, dto.getUsername())
                .betweenIfPresent(LoginLogDO::getCreateTime, dto.getCreateTime());
        if (Boolean.TRUE.equals(dto.getStatus())) {
            query.eq(LoginLogDO::getResult, LoginResultEnum.SUCCESS.getResult());
        } else if (Boolean.FALSE.equals(dto.getStatus())) {
            query.gt(LoginLogDO::getResult, LoginResultEnum.SUCCESS.getResult());
        }
        // 降序
        query.orderByDesc(LoginLogDO::getId);
        return selectPage(dto, query);
    }
}
