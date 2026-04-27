package com.zp.gmall.module.system.service.permission.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.annotations.VisibleForTesting;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.tenant.core.context.TenantContextHolder;
import com.zp.gmall.module.system.controller.admin.permission.dto.RolePageDTO;
import com.zp.gmall.module.system.controller.admin.permission.dto.RoleSaveDTO;
import com.zp.gmall.module.system.controller.admin.permission.dto.RoleUpdateDTO;
import com.zp.gmall.module.system.controller.admin.permission.vo.RoleVO;
import com.zp.gmall.module.system.convert.permission.RoleConvertMapper;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import com.zp.gmall.module.system.enums.permission.RoleCodeEnum;
import com.zp.gmall.module.system.enums.permission.RoleTypeEnum;
import com.zp.gmall.module.system.mapper.permission.RoleMapper;
import com.zp.gmall.module.system.service.permission.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        String tenantId = TenantContextHolder.getTenantId();
        if (StringUtils.isBlank(tenantId)) {
            tenantId = "0";
        }
        // 校验角色
        validateRoleDuplicate(roleSaveDTO.getRoleName(), roleSaveDTO.getRoleCode(), null, tenantId);
        RoleDO roleDO = roleConvertMapper.convert(roleSaveDTO);
        // 设置默认状态为启用
        roleDO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        // 设置默认排序
        if (roleDO.getSort() == null) {
            roleDO.setSort(0);
        }
        baseMapper.insert(roleDO);
        return Convert.toStr(roleDO.getId());
    }

    @Override
    public PageResult<RoleVO> getRolePage(RolePageDTO reqVO){
        String tenantId = TenantContextHolder.getTenantId();
        if (StringUtils.isBlank(tenantId)) {
            tenantId = "0";
        }
        // 构建分页查询条件
        Page<RoleDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        LambdaQueryWrapper<RoleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleDO::getTenantId, tenantId);
        // 按角色名称模糊查询
        if (StringUtils.isNotBlank(reqVO.getRoleName())) {
            queryWrapper.like(RoleDO::getName, reqVO.getRoleName());
        }
        // 按角色编码模糊查询
        if (StringUtils.isNotBlank(reqVO.getRoleCode())) {
            queryWrapper.like(RoleDO::getCode, reqVO.getRoleCode());
        }
        // 按角色类型精确查询
        if (reqVO.getRoleType() != null) {
            queryWrapper.eq(RoleDO::getType, reqVO.getRoleType());
        }
        // 按创建时间倒序排序
        queryWrapper.orderByDesc(RoleDO::getCreateTime);

        // 执行分页查询
        IPage<RoleDO> rolePage = baseMapper.selectPage(page, queryWrapper);

        // 转换为VO
        List<RoleVO> voList = rolePage.getRecords().stream()
                .map(this::convertToRoleVO)
                .collect(Collectors.toList());

        return PageResult.ok(rolePage.getTotal(), voList);
    }

    @Override
    public RoleVO getRoleById(String roleId) {
        RoleDO roleDO = baseMapper.selectById(roleId);
        if (roleDO == null) {
            throw exception(ROLE_NOT_EXISTS);
        }
        return convertToRoleVO(roleDO);
    }

    @Override
    public String updateRole(RoleUpdateDTO roleUpdateDTO) {
        String tenantId = TenantContextHolder.getTenantId();
        if (StringUtils.isBlank(tenantId)) {
            tenantId = "0";
        }
        // 校验角色是否存在
        RoleDO existRole = baseMapper.selectById(roleUpdateDTO.getId());
        if (existRole == null) {
            throw exception(ROLE_NOT_EXISTS);
        }
        // 校验角色唯一性
        validateRoleDuplicate(roleUpdateDTO.getRoleName(), roleUpdateDTO.getRoleCode(), roleUpdateDTO.getId(), tenantId);
        // 更新角色
        RoleDO roleDO = roleConvertMapper.convert(roleUpdateDTO);
        roleDO.setId(roleUpdateDTO.getId());
        baseMapper.updateById(roleDO);
        return roleUpdateDTO.getId();
    }

    @Override
    public void deleteRole(Ids ids) {
        if (CollUtil.isEmpty(ids.getIds())) {
            return;
        }
        // 校验角色是否存在
        for (Serializable id : ids.getIds()) {
            RoleDO roleDO = baseMapper.selectById(id);
            if (roleDO == null) {
                throw exception(ROLE_NOT_EXISTS);
            }
        }
        baseMapper.deleteBatchIds(ids.getIds());
    }

    /**
     * 将RoleDO转换为RoleVO
     */
    private RoleVO convertToRoleVO(RoleDO roleDO) {
        RoleVO roleVO = roleConvertMapper.convert(roleDO);
        // 设置状态名称
        if (roleVO.getStatus() != null) {
            roleVO.setStatusName(CommonStatusEnum.getMessageByStatus(roleVO.getStatus()));
        }
        // 设置角色类型名称
        if (roleVO.getRoleType() != null) {
            RoleTypeEnum roleTypeEnum = RoleTypeEnum.getByType(roleVO.getRoleType());
            roleVO.setRoleTypeName(roleTypeEnum != null ? roleTypeEnum.getLabel() : "");
        }
        return roleVO;
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
