package com.zp.gmall.module.system.service.dept;

import com.zp.gmall.module.system.entity.dept.DeptDO;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/4/10 16:49
 * Version : v1.0.0
 * Description:
 */
public interface IDeptService {

    /**
     * 添加部门
     */
    void addDept(DeptDO dept);

    /**
     * 更新部门信息
     */
    void updateDept(DeptDO dept);

    /**
     * 删除部门
     */
    void deleteDept(Long deptId);

    /**
     * 获取某部门的所有子部门ID
     */
    List<Long> getSubDepartments(Long deptId);

    /**
     * 获取某部门的所有上级部门ID
     */
    List<Long> getParentDepartments(Long deptId);

    /**
     * 获取部门树形结构
     */
    List<DeptDO> getDeptTree();
}
