package com.zp.gmall.module.system.service.dept;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.dept.dto.DeptDTO;
import com.zp.gmall.module.system.controller.admin.dept.dto.DeptPageDTO;
import com.zp.gmall.module.system.controller.admin.dept.vo.DeptVO;
import com.zp.gmall.module.system.entity.dept.DeptDO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.service.dept
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
public interface IDeptService extends IService<DeptDO> {
    
    PageResult<DeptVO> getDeptPage(@Valid DeptPageDTO deptPageDTO);

    Object getDeptTree();

    void deleteDept(@Valid Ids ids);

    void updateDept(@Valid DeptDTO deptDTO);

    void createDept(@Valid DeptDTO deptDTO);

    DeptVO getDeptDetail(@Valid @NotNull(message = "部门ID不能为空") String id);
}
