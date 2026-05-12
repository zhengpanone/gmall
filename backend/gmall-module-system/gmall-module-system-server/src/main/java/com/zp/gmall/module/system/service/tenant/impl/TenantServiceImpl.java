package com.zp.gmall.module.system.service.tenant.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantDTO;
import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantPageDTO;
import com.zp.gmall.module.system.controller.admin.tenant.vo.TenantVO;
import com.zp.gmall.module.system.convert.tenant.TenantConvert;
import com.zp.gmall.module.system.entity.tenant.TenantDO;
import com.zp.gmall.module.system.mapper.tenant.TenantMapper;
import com.zp.gmall.module.system.service.tenant.ITenantService;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.zp.gmall.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.gmall.module.system.enums.ErrorCodeConstants.TENANT_NOT_EXISTS;

/**
 *
 * Description: 租户
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, TenantDO> implements ITenantService {
    private final TenantConvert convertMapper = Mappers.getMapper(TenantConvert.class);

    @Override
    public void create(TenantDTO dto) {
        TenantDO tenantDO = convertMapper.convert(dto);
        baseMapper.insert(tenantDO);
    }

    @Override
    public TenantVO queryById(String id) {
        TenantDO tenantDO = baseMapper.selectById(id);
        if (ObjectUtil.isNull(tenantDO)) {
            throw exception(TENANT_NOT_EXISTS);
        }
        return convertMapper.convert(tenantDO);
    }

    @Override
    public PageResult<TenantVO> getPageList(TenantPageDTO dto) {
        Page<TenantDO> page = new Page<>(dto.getPageNo(), dto.getPageSize());
        LambdaQueryWrapper<TenantDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(dto.getName()), TenantDO::getName, dto.getName());
        queryWrapper.eq(StringUtils.isNotBlank(dto.getStatus()), TenantDO::getStatus, dto.getStatus());
        IPage<TenantDO> tenantPage = baseMapper.selectPage(page, queryWrapper);

        // 转换为VO
        List<TenantVO> voList = tenantPage.getRecords().stream()
                .map(convertMapper::convert)
                .collect(Collectors.toList());

        return PageResult.ok(tenantPage.getTotal(), voList);
    }

    @Override
    public void deleteByIds(Ids ids) {
        baseMapper.deleteByIds(ids.getIds());
    }

    @Override
    public void updateById(TenantDTO tenantDTO) {
        TenantDO tenantDO = convertMapper.convert(tenantDTO);
        baseMapper.updateById(tenantDO);
    }
}
