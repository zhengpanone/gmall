package com.zp.module.system.service.dept.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.google.common.annotations.VisibleForTesting;
import com.zp.framework.common.util.object.BeanUtils;
import com.zp.module.system.controller.admin.dept.dto.DeptListDTO;
import com.zp.module.system.controller.admin.dept.dto.DeptSaveDTO;
import com.zp.module.system.dal.dataobject.dept.DeptDO;
import com.zp.module.system.dao.dept.DeptMapper;
import com.zp.module.system.service.dept.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import static com.zp.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.framework.common.util.collection.CollectionUtils.convertSet;
import static com.zp.module.system.enums.ErrorCodeConstants.*;

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
    private final DeptMapper deptMapper;

    public DeptServiceImpl(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    /**
     * 创建部门
     *
     * @param createDTO 部门信息
     * @return 部门编号
     */
    @Override
    public String createDept(DeptSaveDTO createDTO) {
        if (createDTO.getParentId() == null) {
            createDTO.setParentId(DeptDO.PARENT_ID_ROOT);
        }
        // 校验父部门的有效性
        validateParentDept(null, createDTO.getParentId());
        // 校验部门名的唯一性
        validateDeptNameUnique(null, createDTO.getParentId(), createDTO.getName());
        DeptDO dept = BeanUtils.toBean(createDTO, DeptDO.class);
        deptMapper.insert(dept);
        return dept.getId();
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
        // 校验部门是否存在
        validateDeptExists(id);
        // 校验是否有子部门
        if (deptMapper.selectCountByParentId(id) > 0) {
            throw exception(DEPT_EXITS_CHILDREN);
        }
        // 删除部门
        deptMapper.deleteById(id);
    }

    /**
     * 获得部门信息
     *
     * @param id 部门编号
     * @return 部门信息
     */
    @Override
    public DeptDO getDept(String id) {
        return deptMapper.selectById(id);
    }

    /**
     * 获得部门信息数组
     *
     * @param ids 部门编号数组
     * @return 部门信息数组
     */
    @Override
    public List<DeptDO> getDeptList(Collection<String> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return deptMapper.selectBatchIds(ids);
    }

    /**
     * 筛选部门列表
     *
     * @param dto 筛选条件请求 VO
     * @return 部门列表
     */
    @Override
    public List<DeptDO> getDeptList(DeptListDTO dto) {
        List<DeptDO> list = deptMapper.selectList(dto);
        list.sort(Comparator.comparing(DeptDO::getSort));
        return list;
    }

    /**
     * 获得指定部门的所有子部门
     *
     * @param id 部门编号
     * @return 子部门列表
     */
    @Override
    public List<DeptDO> getChildDeptList(String id) {
        List<DeptDO> children = new LinkedList<>();
        // 遍历每一层
        Collection<String> parentIds = Collections.singleton(id);
        // 使用 Short.MAX_VALUE 避免 bug 场景下，存在死循环
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 查询当前层，所有的子部门
            List<DeptDO> deptList = deptMapper.selectListByParentId(parentIds);
            // 1. 如果没有子部门，则结束遍历
            if (CollUtil.isEmpty(deptList)) {
                break;
            }
            // 2. 如果有子部门，继续遍历
            children.addAll(deptList);
            parentIds = convertSet(deptList, DeptDO::getId);
        }
        return children;
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

    @VisibleForTesting
    void validateDeptNameUnique(String id, String parentId, String name) {
        DeptDO dept = deptMapper.selectByParentIdAndName(parentId, name);
        if (dept == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的部门
        if (id == null) {
            throw exception(DEPT_NAME_DUPLICATE);
        }
        if (ObjectUtil.notEqual(dept.getId(), id)) {
            throw exception(DEPT_NAME_DUPLICATE);
        }
    }

    @VisibleForTesting
    void validateParentDept(String id, String parentId) {
        if (parentId == null || DeptDO.PARENT_ID_ROOT.equals(parentId)) {
            return;
        }
        // 1. 不能设置自己为父部门
        if (Objects.equals(id, parentId)) {
            throw exception(DEPT_PARENT_ERROR);
        }
        // 2. 父部门不存在
        DeptDO parentDept = deptMapper.selectById(parentId);
        if (parentDept == null) {
            throw exception(DEPT_PARENT_NOT_EXITS);
        }
        // 3. 递归校验父部门，如果父部门是自己的子部门，则报错，避免形成环路
        // id 为空，说明新增，不需要考虑环路
        if (id == null) {
            return;
        }
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 3.1 校验环路
            parentId = parentDept.getParentId();
            if (Objects.equals(id, parentId)) {
                throw exception(DEPT_PARENT_IS_CHILD);
            }
            // 3.2 继续递归下一级父部门
            if (parentId == null || DeptDO.PARENT_ID_ROOT.equals(parentId)) {
                break;
            }
            parentDept = deptMapper.selectById(parentId);
            if (parentDept == null) {
                break;
            }
        }
    }


    @VisibleForTesting
    void validateDeptExists(String id) {
        if (id == null) {
            return;
        }
        DeptDO dept = deptMapper.selectById(id);
        if (dept == null) {
            throw exception(DEPT_NOT_FOUND);
        }
    }

}
