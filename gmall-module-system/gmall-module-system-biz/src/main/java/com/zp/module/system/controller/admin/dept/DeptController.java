package com.zp.module.system.controller.admin.dept;

import com.zp.framework.common.enums.CommonStatusEnum;
import com.zp.framework.common.pojo.Result;
import com.zp.framework.common.util.object.BeanUtils;
import com.zp.module.system.controller.admin.dept.dto.DeptListDTO;
import com.zp.module.system.controller.admin.dept.dto.DeptSaveDTO;
import com.zp.module.system.controller.admin.dept.vo.DeptSimpleVO;
import com.zp.module.system.controller.admin.dept.vo.DeptVO;
import com.zp.module.system.dal.dataobject.dept.DeptDO;
import com.zp.module.system.service.dept.DeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zp.framework.common.pojo.Result.ok;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 12:35
 * Version : v1.0.0
 * Description: 部门管理
 */
@Tag(name = "管理后台 - 部门")
@RestController
@RequestMapping("/system/dept")
@Validated
public class DeptController {
    @Resource
    private DeptService deptService;

    @PostMapping("create")
    @Operation(summary = "创建部门")
    //@PreAuthorize("@ss.hasPermission('system:dept:create')")
    public Result<String> createDept(@Valid @RequestBody DeptSaveDTO createDTO) {
        String deptId = deptService.createDept(createDTO);
        return ok(deptId);
    }

    @PutMapping("update")
    @Operation(summary = "更新部门")
    //@PreAuthorize("@ss.hasPermission('system:dept:update')")
    public Result<Boolean> updateDept(@Valid @RequestBody DeptSaveDTO updateDTO) {
        deptService.updateDept(updateDTO);
        return ok(true);
    }

    @DeleteMapping("delete")
    @Operation(summary = "删除部门")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('system:dept:delete')")
    public Result<Boolean> deleteDept(@RequestParam("id") String id) {
        deptService.deleteDept(id);
        return ok(true);
    }

    @GetMapping("/list")
    @Operation(summary = "获取部门列表")
    //@PreAuthorize("@ss.hasPermission('system:dept:query')")
    public Result<List<DeptVO>> getDeptList(DeptListDTO reqDTO) {
        List<DeptDO> list = deptService.getDeptList(reqDTO);
        return ok(BeanUtils.toBean(list, DeptVO.class));
    }

    @GetMapping(value = {"/list-all-simple", "/simple-list"})
    @Operation(summary = "获取部门精简信息列表", description = "只包含被开启的部门，主要用于前端的下拉选项")
    public Result<List<DeptSimpleVO>> getSimpleDeptList() {
        List<DeptDO> list = deptService.getDeptList(new DeptListDTO().setStatus(CommonStatusEnum.ENABLE.getStatus()));
        return ok(BeanUtils.toBean(list, DeptSimpleVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得部门信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('system:dept:query')")
    public Result<DeptVO> getDept(@RequestParam("id") String id) {
        DeptDO dept = deptService.getDept(id);
        return ok(BeanUtils.toBean(dept, DeptVO.class));
    }
}
