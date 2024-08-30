package com.zp.module.system.service.dept;

import com.zp.framework.common.util.collection.CollectionUtils;
import com.zp.module.system.controller.admin.dept.dto.DeptListDTO;
import com.zp.module.system.controller.admin.dept.dto.DeptSaveDTO;
import com.zp.module.system.dal.dataobject.dept.DeptDO;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 12:31
 * Version : v1.0.0
 * Description: 部门 Service 接口
 */
public interface DeptService {
    /**
     * 创建部门
     *
     * @param createDTO 部门信息
     * @return 部门编号
     */
    String createDept(DeptSaveDTO createDTO);

    /**
     * 更新部门
     *
     * @param updateDTO 部门信息
     */
    void updateDept(DeptSaveDTO updateDTO);

    /**
     * 删除部门
     *
     * @param id 部门编号
     */
    void deleteDept(String id);

    /**
     * 获得部门信息
     *
     * @param id 部门编号
     * @return 部门信息
     */
    DeptDO getDept(String id);

    /**
     * 获得部门信息数组
     *
     * @param ids 部门编号数组
     * @return 部门信息数组
     */
    List<DeptDO> getDeptList(Collection<String> ids);

    /**
     * 筛选部门列表
     *
     * @param dto 筛选条件请求 VO
     * @return 部门列表
     */
    List<DeptDO> getDeptList(DeptListDTO dto);

    /**
     * 获得指定编号的部门 Map
     *
     * @param ids 部门编号数组
     * @return 部门 Map
     */
    default Map<String, DeptDO> getDeptMap(Collection<String> ids) {
        List<DeptDO> list = getDeptList(ids);
        return CollectionUtils.convertMap(list, DeptDO::getId);
    }

    /**
     * 获得指定部门的所有子部门
     *
     * @param id 部门编号
     * @return 子部门列表
     */
    List<DeptDO> getChildDeptList(String id);

    /**
     * 获得所有子部门，从缓存中
     *
     * @param id 父部门编号
     * @return 子部门列表
     */
    Set<String> getChildDeptIdListFromCache(String id);

    /**
     * 校验部门们是否有效。如下情况，视为无效：
     * 1. 部门编号不存在
     * 2. 部门被禁用
     *
     * @param ids 角色编号数组
     */
    void validateDeptList(Collection<String> ids);
}
