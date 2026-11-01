package com.zp.gmall.module.system.controller.admin.tenant;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.framework.validation.group.CreateGroup;
import com.zp.gmall.framework.validation.group.UpdateGroup;
import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantPackageDTO;
import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantPackagePageDTO;
import com.zp.gmall.module.system.controller.admin.tenant.vo.TenantPackageVO;
import com.zp.gmall.module.system.service.tenant.ITenantPackageService;
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
 * Description: 租户套餐
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Tag(name = "管理后台 - 租户套餐")
@RestController
@RequestMapping("/tenantPackage")
@RequiredArgsConstructor
@Validated
public class TenantPackageController {

    @Resource
    private final ITenantPackageService tenantPackageService;

    @PostMapping("/create")
    @Operation(summary = "新增租户套餐")
    public Result<?> create(@RequestBody @Validated(CreateGroup.class) @Valid TenantPackageDTO tenantPackageDTO) {
        tenantPackageService.create(tenantPackageDTO);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新租户套餐")
    public Result<?> updateById(@RequestBody @Validated(UpdateGroup.class) @Valid TenantPackageDTO tenantPackageDTO) {
        tenantPackageService.updateById(tenantPackageDTO);
        return Result.ok();
    }


    @DeleteMapping("/delete")
    @Operation(summary = "删除租户套餐")
    public Result<?> deleteByIds(@RequestBody @Valid Ids ids) {
        tenantPackageService.deleteByIds(ids);
        return Result.ok();
    }

    @GetMapping("/page")
    @Operation(summary = "获取租户套餐分页")
    public PageResult<TenantPackageVO> getPageList(@Valid TenantPackagePageDTO tenantPackagePageDTO) {
        return tenantPackageService.getPageList(tenantPackagePageDTO);
    }

    @GetMapping("/get")
    @Operation(summary = "获取租户套餐详情")
    public Result<TenantPackageVO> getById(@Valid @NotNull(message = "租户套餐ID不能为空") String id) {
        return Result.ok(tenantPackageService.queryById(id));
    }
}
