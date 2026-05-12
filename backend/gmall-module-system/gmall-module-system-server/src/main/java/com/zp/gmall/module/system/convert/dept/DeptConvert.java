package com.zp.gmall.module.system.convert.dept;

import com.zp.gmall.module.system.controller.admin.dept.dto.DeptDTO;
import com.zp.gmall.module.system.controller.admin.dept.vo.DeptVO;
import com.zp.gmall.module.system.entity.dept.DeptDO;
import org.mapstruct.Mapper;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.convert.dept
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Mapper(componentModel = "spring")
public interface DeptConvert {

    DeptDO convert(DeptDTO dto);

    DeptVO doToVo(DeptDO deptDO);
}
