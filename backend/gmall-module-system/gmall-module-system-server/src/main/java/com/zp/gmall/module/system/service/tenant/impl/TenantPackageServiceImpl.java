package com.zp.gmall.module.system.service.tenant.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantPackageDTO;
import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantPackagePageDTO;
import com.zp.gmall.module.system.controller.admin.tenant.vo.TenantPackageVO;
import com.zp.gmall.module.system.convert.tenant.TenantPackageConvertMapper;
import com.zp.gmall.module.system.entity.tenant.TenantPackageDO;
import com.zp.gmall.module.system.mapper.tenant.TenantPackageMapper;
import com.zp.gmall.module.system.service.tenant.ITenantPackageService;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.zp.gmall.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.gmall.module.system.enums.ErrorCodeConstants.TENANT_PACKAGE_NOT_EXISTS;

/**
 *
 * Description: 租户套餐
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Service
public class TenantPackageServiceImpl extends ServiceImpl<TenantPackageMapper, TenantPackageDO> implements ITenantPackageService {

    private final TenantPackageConvertMapper convertMapper = Mappers.getMapper(TenantPackageConvertMapper.class);

    @Override
    public void create(TenantPackageDTO tenantPackageDTO) {
        TenantPackageDO tenantPackageDO = convertMapper.convert(tenantPackageDTO);
        baseMapper.insert(tenantPackageDO);
    }

    @Override
    public TenantPackageVO queryById(String id) {
        TenantPackageDO tenantPackageDO = baseMapper.selectById(id);
        if (ObjectUtil.isNull(tenantPackageDO)) {
            throw exception(TENANT_PACKAGE_NOT_EXISTS);
        }
        return convertMapper.convert(tenantPackageDO);
    }

    @Override
    public PageResult<TenantPackageVO> getPageList(TenantPackagePageDTO dto) {
        Page<TenantPackageDO> page = new Page<>(dto.getPageNo(), dto.getPageSize());
        LambdaQueryWrapper<TenantPackageDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(dto.getPackageName()), TenantPackageDO::getName, dto.getPackageName());
        queryWrapper.eq(StringUtils.isNotBlank(dto.getStatus()), TenantPackageDO::getStatus, dto.getStatus());
        IPage<TenantPackageDO> packagePage = baseMapper.selectPage(page, queryWrapper);

        // 转换为VO
        List<TenantPackageVO> voList = packagePage.getRecords().stream()
                .map(convertMapper::convert)
                .collect(Collectors.toList());

        return PageResult.ok(packagePage.getTotal(), voList);
    }

    /**
     * 查询租户套餐列表
     */
    @Override
    public List<TenantPackageVO> queryList(TenantPackageDTO dto) {
        LambdaQueryWrapper<TenantPackageDO> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(dto.getPackageName()), TenantPackageDO::getName, dto.getPackageName());
        lqw.eq(StringUtils.isNotBlank(dto.getStatus()), TenantPackageDO::getStatus, dto.getStatus());
        List<TenantPackageDO> doList = baseMapper.selectList(lqw);
        return convertMapper.convert(doList);
    }

    @Override
    public void deleteByIds(Ids ids) {
        baseMapper.deleteByIds(ids.getIds());
    }

    @Override
    public void updateById(TenantPackageDTO tenantPackageDTO) {
        TenantPackageDO tenantPackageDO = convertMapper.convert(tenantPackageDTO);
        baseMapper.updateById(tenantPackageDO);
    }


}
