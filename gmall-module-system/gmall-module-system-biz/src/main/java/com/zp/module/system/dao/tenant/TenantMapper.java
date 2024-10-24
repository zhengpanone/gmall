package com.zp.module.system.dao.tenant;

import com.zp.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.module.system.dal.dataobject.tenant.TenantDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * Author : zhengpanone
 * Date : 2024/7/7 11:54
 * Version : v1.0.0
 * Description: TODO
 */
@Mapper
public interface TenantMapper extends BaseMapperX<TenantDO> {

    default TenantDO selectByWebsite(String website) {
        return selectOne(TenantDO::getWebsite, website);
    }
}
