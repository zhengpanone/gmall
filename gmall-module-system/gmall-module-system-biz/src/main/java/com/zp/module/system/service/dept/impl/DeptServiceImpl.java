package com.zp.module.system.service.dept.impl;

import com.zp.module.system.controller.admin.dept.dto.DeptListDTO;
import com.zp.module.system.controller.admin.dept.dto.DeptSaveDTO;
import com.zp.module.system.dal.dataobject.dept.DeptDO;
import com.zp.module.system.service.dept.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 12:43
 * Version : v1.0.0
 * Description: 部门 Service 实现类
 */
@Service
@Validated
@Slf4j
public class DeptServiceImpl implements DeptService {
    /**
     * 创建部门
     *
     * @param createDTO 部门信息
     * @return 部门编号
     */
    @Override
    public String createDept(DeptSaveDTO createDTO) {
        return "";
    }

    /**
     * 更新部门
     *
     * @param updateDTO 部门信息
     */
    @Override
    public void updateDept(DeptSaveDTO updateDTO) {

    }

    /**
     * 删除部门
     *
     * @param id 部门编号
     */
    @Override
    public void deleteDept(String id) {

    }

    /**
     * 获得部门信息
     *
     * @param id 部门编号
     * @return 部门信息
     */
    @Override
    public DeptDO getDept(String id) {
        return null;
    }

    /**
     * 获得部门信息数组
     *
     * @param ids 部门编号数组
     * @return 部门信息数组
     */
    @Override
    public List<DeptDO> getDeptList(Collection<String> ids) {
        return List.of();
    }

    /**
     * 筛选部门列表
     *
     * @param dto 筛选条件请求 VO
     * @return 部门列表
     */
    @Override
    public List<DeptDO> getDeptList(DeptListDTO dto) {
        return List.of();
    }

    /**
     * 获得指定部门的所有子部门
     *
     * @param id 部门编号
     * @return 子部门列表
     */
    @Override
    public List<DeptDO> getChildDeptList(String id) {
        return List.of();
    }

    /**
     * 获得所有子部门，从缓存中
     *
     * @param id 父部门编号
     * @return 子部门列表
     */
    @Override
    public Set<String> getChildDeptIdListFromCache(String id) {
        return Set.of();
    }

    /**
     * 校验部门们是否有效。如下情况，视为无效：
     * 1. 部门编号不存在
     * 2. 部门被禁用
     *
     * @param ids 角色编号数组
     */
    @Override
    public void validateDeptList(Collection<String> ids) {

    }
}
