package com.zp.module.system.service.tenant;

import com.zp.framework.common.pojo.PageResult;
import com.zp.framework.tenant.core.context.TenantContextHolder;
import com.zp.module.system.controller.admin.tenant.dto.TenantPageDTO;
import com.zp.module.system.controller.admin.tenant.dto.TenantSaveDTO;
import com.zp.module.system.dal.dataobject.tenant.TenantDO;
import com.zp.module.system.service.tenant.handler.TenantInfoHandler;
import com.zp.module.system.service.tenant.handler.TenantMenuHandler;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Set;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 11:35
 * Version : v1.0.0
 * Description: 租户 Service 接口
 */
public interface TenantService {
    /**
     * 创建租户
     *
     * @param createDTO 创建信息
     * @return 编号
     */
    String createTenant(@Valid TenantSaveDTO createDTO);

    /**
     * 更新租户
     *
     * @param updateDTO 更新信息
     */
    void updateTenant(@Valid TenantSaveDTO updateDTO);

    /**
     * 更新租户的角色菜单
     *
     * @param tenantId 租户编号
     * @param menuIds  菜单编号数组
     */
    void updateTenantRoleMenu(String tenantId, Set<String> menuIds);

    /**
     * 删除租户
     *
     * @param id 编号
     */
    void deleteTenant(String id);

    /**
     * 获得租户
     *
     * @param id 编号
     * @return 租户
     */
    TenantDO getTenant(String id);

    /**
     * 获得域名对应的租户
     *
     * @param website 域名
     * @return 租户
     */
    TenantDO getTenantByWebsite(String website);


    /**
     * 进行租户的信息处理逻辑
     * 其中，租户编号从 {@link TenantContextHolder} 上下文中获取
     *
     * @param handler 处理器
     */
    void handleTenantInfo(TenantInfoHandler handler);

    /**
     * 获得租户分页
     *
     * @param pageDTO 分页查询
     * @return 租户分页
     */
    PageResult<TenantDO> getTenantPage(TenantPageDTO pageDTO);

    /**
     * 获得名字对应的租户
     *
     * @param name 租户名
     * @return 租户
     */
    TenantDO getTenantByName(String name);


    /**
     * 获得使用指定套餐的租户数量
     *
     * @param packageId 租户套餐编号
     * @return 租户数量
     */
    String getTenantCountByPackageId(String packageId);

    /**
     * 获得使用指定套餐的租户数组
     *
     * @param packageId 租户套餐编号
     * @return 租户数组
     */
    List<TenantDO> getTenantListByPackageId(String packageId);


    /**
     * 进行租户的菜单处理逻辑
     * 其中，租户编号从 {@link TenantContextHolder} 上下文中获取
     *
     * @param handler 处理器
     */
    void handleTenantMenu(TenantMenuHandler handler);

    /**
     * 获得所有租户
     *
     * @return 租户编号数组
     */
    List<String> getTenantIdList();

    /**
     * 校验租户是否合法
     *
     * @param id 租户编号
     */
    void validTenant(String id);
}
