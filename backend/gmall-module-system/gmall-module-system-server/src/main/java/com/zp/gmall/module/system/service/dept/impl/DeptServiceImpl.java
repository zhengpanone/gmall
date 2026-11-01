package com.zp.gmall.module.system.service.dept.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.dept.dto.DeptDTO;
import com.zp.gmall.module.system.controller.admin.dept.dto.DeptPageDTO;
import com.zp.gmall.module.system.controller.admin.dept.vo.DeptVO;
import com.zp.gmall.module.system.convert.dept.DeptConvert;
import com.zp.gmall.module.system.entity.dept.DeptDO;
import com.zp.gmall.module.system.mapper.dept.DeptMapper;
import com.zp.gmall.module.system.service.dept.IDeptService;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.service.dept.impl
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, DeptDO> implements IDeptService {

    private final DeptConvert deptConvertMapper = Mappers.getMapper(DeptConvert.class);

    @Override
    public PageResult<DeptVO> getDeptPage(DeptPageDTO deptPageDTO) {
        Page<DeptDO> page = new Page<>(deptPageDTO.getPageNo(), deptPageDTO.getPageSize());
        LambdaQueryWrapper<DeptDO> queryWrapper = new LambdaQueryWrapper<DeptDO>()
                .like(StringUtils.isNotBlank(deptPageDTO.getDeptName()), DeptDO::getName, deptPageDTO.getDeptName())
                .eq(StringUtils.isNotBlank(deptPageDTO.getDeptCode()), DeptDO::getCode, deptPageDTO.getDeptCode());
        Page<DeptDO> deptPage = baseMapper.selectPage(page, queryWrapper);
        List<DeptVO> voList = deptPage.getRecords().stream()
                .map(deptConvertMapper::doToVo).toList();
        return PageResult.ok(deptPage.getTotal(), deptPage.getCurrent(), deptPage.getSize(), voList);
    }

    @Override
    public Object getDeptTree() {
        return null;
    }

    @Override
    public void deleteDept(Ids ids) {
        removeByIds(ids.getIds());
    }

    @Override
    public void updateDept(DeptDTO deptDTO) {
        DeptDO deptDO = deptConvertMapper.convert(deptDTO);
        updateById(deptDO);
    }

    @Override
    public void createDept(DeptDTO deptDTO) {
        DeptDO deptDO = deptConvertMapper.convert(deptDTO);
        save(deptDO);
    }

    @Override
    public DeptVO getDeptDetail(String id) {
        return null;
    }
}
