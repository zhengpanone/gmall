package com.zp.gmall.module.system.controller.admin.tenant;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.framework.validation.group.CreateGroup;
import com.zp.gmall.framework.validation.group.UpdateGroup;
import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantDTO;
import com.zp.gmall.module.system.controller.admin.tenant.dto.TenantPageDTO;
import com.zp.gmall.module.system.controller.admin.tenant.vo.TenantVO;
import com.zp.gmall.module.system.service.tenant.ITenantService;
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
 * Description: 租户
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Tag(name = "管理后台 - 租户")
@RestController
@RequestMapping("/tenant")
@RequiredArgsConstructor
@Validated
public class TenantController {

    @Resource
    private final ITenantService tenantService;

    @PostMapping("/create")
    @Operation(summary = "新增租户")
    public Result<?> create(@RequestBody @Validated(CreateGroup.class) @Valid TenantDTO tenantDTO) {
        tenantService.create(tenantDTO);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新租户")
    public Result<?> updateById(@RequestBody @Validated(UpdateGroup.class) @Valid TenantDTO tenantDTO) {
        tenantService.updateById(tenantDTO);
        return Result.ok();
    }


    @DeleteMapping("/delete")
    @Operation(summary = "删除租户")
    public Result<?> deleteByIds(@RequestBody @Valid Ids ids) {
        tenantService.deleteByIds(ids);
        return Result.ok();
    }

    @GetMapping("/page")
    @Operation(summary = "获取租户分页")
    public PageResult<TenantVO> getPageList(@Valid TenantPageDTO tenantPageDTO) {
        return tenantService.getPageList(tenantPageDTO);
    }

    @GetMapping("/get")
    @Operation(summary = "获取租户详情")
    public Result<TenantVO> queryById(@Valid @NotNull(message = "租户ID不能为空") String id) {
        return Result.ok(tenantService.queryById(id));
    }
}
