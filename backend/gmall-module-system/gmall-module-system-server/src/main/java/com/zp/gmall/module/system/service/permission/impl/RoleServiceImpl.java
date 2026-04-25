package com.zp.gmall.module.system.service.permission.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.annotations.VisibleForTesting;
import com.zp.gmall.framework.common.pojo.Ids;
import com.zp.gmall.framework.common.pojo.PageResult;
import com.zp.gmall.framework.tenant.core.context.TenantContextHolder;
import com.zp.gmall.module.system.controller.admin.permission.dto.RolePageDTO;
import com.zp.gmall.module.system.controller.admin.permission.dto.RoleSaveDTO;
import com.zp.gmall.module.system.controller.admin.permission.dto.RoleUpdateDTO;
import com.zp.gmall.module.system.controller.admin.permission.vo.RoleVO;
import com.zp.gmall.module.system.convert.permission.RoleConvertMapper;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import com.zp.gmall.module.system.enums.permission.RoleCodeEnum;
import com.zp.gmall.module.system.mapper.permission.RoleMapper;
import com.zp.gmall.module.system.service.permission.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import static com.zp.gmall.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.gmall.module.system.enums.ErrorCodeConstants.*;

/**
 * @author : zhengpanone
 * Date : 2026/4/24 23:39
 * Version : v1.0.0
 * Description:
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements IRoleService {

    private final RoleConvertMapper roleConvertMapper = Mappers.getMapper(RoleConvertMapper.class);

    @Override
    public String createRole(RoleSaveDTO roleSaveDTO) {
        // 1. 校验角色名称是否存在
        String tenantId = TenantContextHolder.getTenantId();
        if (StringUtils.isBlank(tenantId)) {
            tenantId = "0";
        }
        // 1. 校验角色
        validateRoleDuplicate(roleSaveDTO.getRoleName(), roleSaveDTO.getRoleCode(), null, tenantId);
        RoleDO roleDO = roleConvertMapper.convert(roleSaveDTO);
        baseMapper.insert(roleDO);
        return Convert.toStr(roleDO.getId());
    }

    @Override
    public PageResult<RoleVO> getRolePage(RolePageDTO reqVO) {
        return null;
    }

    @Override
    public RoleVO getRoleById(String roleId) {
        return null;
    }

    @Override
    public String updateRole(RoleUpdateDTO roleUpdateDTO) {
        return "";
    }

    @Override
    public void deleteRole(Ids ids) {
        baseMapper.deleteBatchIds(ids.getIds());
    }


    /**
     * 校验角色的唯一字段是否重复
     * <p>
     * 1. 是否存在相同名字的角色
     * 2. 是否存在相同编码的角色
     *
     * @param name 角色名字
     * @param code 角色额编码
     * @param id   角色编号
     */
    @VisibleForTesting
    void validateRoleDuplicate(String name, String code, String id, String tenantId) {
        // 0. 超级管理员，不允许创建
        if (RoleCodeEnum.isSuperAdmin(code)) {
            throw exception(ROLE_ADMIN_CODE_ERROR, code);
        }
        // 1. 该 name 名字被其它角色所使用
        RoleDO role = baseMapper.selectByName(name, tenantId);
        if (role != null && !role.getId().equals(id)) {
            throw exception(ROLE_NAME_DUPLICATE, name);
        }
        // 该 code 编码被其它角色所使用
        role = baseMapper.selectByCode(code, tenantId);
        if (role != null && !role.getId().equals(id)) {
            throw exception(ROLE_CODE_DUPLICATE, code);
        }
    }
}
