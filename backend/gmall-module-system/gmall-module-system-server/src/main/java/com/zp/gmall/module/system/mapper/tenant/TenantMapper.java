package com.zp.gmall.module.system.mapper.tenant;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.module.system.entity.tenant.TenantDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.zp.gmall.framework.mybatis.core.util.MyBatisUtils.findInSet;

/**
 *
 * Description: 租户
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Mapper
public interface TenantMapper extends BaseMapperX<TenantDO> {

    default List<TenantDO> selectListByWebsite(String websites) {
        LambdaQueryWrapper<TenantDO> wrapper = Wrappers.lambdaQuery();
        findInSet(wrapper, TenantDO::getWebsites, websites);
        return selectList(wrapper);
    }
}
