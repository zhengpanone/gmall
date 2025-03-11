package com.zp.gmall.module.system.service.dept.impl;

import com.zp.gmall.module.system.entity.dept.DeptDO;
import com.zp.gmall.module.system.mapper.dept.DeptMapper;
import com.zp.gmall.module.system.service.dept.IDeptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/4/10 16:50
 * Version : v1.0.0
 * Description:
 */
@Service
@AllArgsConstructor
public class DeptServiceImpl implements IDeptService {

    private final DeptMapper deptMapper;


    @Override
    public void addDept(DeptDO dept) {
        deptMapper.insert(dept);
    }

    @Override
    public void updateDept(DeptDO dept) {
        deptMapper.updateById(dept);
        // TODO更新闭包逻辑
    }

    @Override
    public void deleteDept(Long deptId) {
        deptMapper.deleteById(deptId);
    }

    @Override
    public List<Long> getSubDepartments(Long deptId) {
        return List.of();
    }

    @Override
    public List<Long> getParentDepartments(Long deptId) {
        return List.of();
    }

    @Override
    public List<DeptDO> getDeptTree() {
        // TODO
        return List.of();
    }
}
