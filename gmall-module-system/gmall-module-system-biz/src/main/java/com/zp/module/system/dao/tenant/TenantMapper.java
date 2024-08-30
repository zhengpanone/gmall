package com.zp.module.system.dao.tenant;

import com.zp.framework.common.pojo.PageResult;
import com.zp.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zp.module.system.controller.admin.tenant.dto.TenantPageDTO;
import com.zp.module.system.dal.dataobject.tenant.TenantDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * Author : zhengpanone
 * Date : 2024/7/7 11:54
 * Version : v1.0.0
 * Description: 租户 Mapper
 */
@Mapper
public interface TenantMapper extends BaseMapperX<TenantDO> {

    default PageResult<TenantDO> selectPage(TenantPageDTO dto) {
        return selectPage(dto, new LambdaQueryWrapperX<TenantDO>()
                .likeIfPresent(TenantDO::getName, dto.getName())
                .likeIfPresent(TenantDO::getContactName, dto.getContactName())
                .likeIfPresent(TenantDO::getContactMobile, dto.getContactMobile())
                .eqIfPresent(TenantDO::getStatus, dto.getStatus())
                .betweenIfPresent(TenantDO::getCreateTime, dto.getCreateTime())
                .orderByDesc(TenantDO::getId));
    }

    default TenantDO selectByName(String name) {
        return selectOne(TenantDO::getName, name);
    }

    default TenantDO selectByWebsite(String website) {
        return selectOne(TenantDO::getWebsite, website);
    }

    default Long selectCountByPackageId(Long packageId) {
        return selectCount(TenantDO::getPackageId, packageId);
    }

    default List<TenantDO> selectListByPackageId(Long packageId) {
        return selectList(TenantDO::getPackageId, packageId);
    }

}
