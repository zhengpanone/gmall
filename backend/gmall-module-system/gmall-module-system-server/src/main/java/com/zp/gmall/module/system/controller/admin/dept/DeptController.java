package com.zp.gmall.module.system.controller.admin.dept;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.framework.validation.group.CreateGroup;
import com.zp.gmall.framework.validation.group.UpdateGroup;
import com.zp.gmall.module.system.controller.admin.dept.dto.DeptDTO;
import com.zp.gmall.module.system.controller.admin.dept.dto.DeptPageDTO;
import com.zp.gmall.module.system.controller.admin.dept.vo.DeptVO;
import com.zp.gmall.module.system.service.dept.IDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.controller.admin.dept
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Tag(name = "管理后台 - 部门")
@RestController
@RequestMapping("/dept")
@RequiredArgsConstructor
@Validated
public class DeptController {

    @Resource
    private final IDeptService deptService;

    @PostMapping("/create")
    @Operation(summary = "新增部门")
    public Result<?> createDept(@RequestBody @Validated(CreateGroup.class) @Valid DeptDTO deptDTO) {
        deptService.createDept(deptDTO);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新部门")
    public Result<?> updateDept(@RequestBody @Validated(UpdateGroup.class) @Valid DeptDTO deptDTO) {
        deptService.updateDept(deptDTO);
        return Result.ok();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除部门")
    public Result<?> deleteDept(@RequestBody @Valid Ids ids) {
        deptService.deleteDept(ids);
        return Result.ok();
    }

    @GetMapping("/page")
    @Operation(summary = "获取部门分页")
    public PageResult<DeptVO> getDeptPage(@Valid DeptPageDTO deptPageDTO) {
     return deptService.getDeptPage(deptPageDTO);
    }

    @GetMapping("/tree")
    @Operation(summary = "获取部门树")
    public Result<?> getDeptTree() {
        return Result.ok(deptService.getDeptTree());
    }

    @GetMapping("/get")
    @Operation(summary = "获取部门详情")
    public Result<DeptVO> getDeptDetail(@Valid @NotNull(message = "部门ID不能为空") String id) {
        return Result.ok(deptService.getDeptDetail(id));
    }
}
