package com.zp.module.system.controller.admin.tenant;

import com.zp.framework.common.pojo.Result;
import com.zp.module.system.service.tenant.TenantPackageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 11:30
 * Version : v1.0.0
 * Description: 租户套餐
 */
@Tag(name = "管理后台 - 租户套餐")
@RestController
@RequestMapping("/system/tenant-package")
@Validated
public class TenantPackageController {
    @Resource
    private TenantPackageService tenantPackageService;

    @PostMapping("/create")
    @Operation(summary = "创建租户套餐")
    @PreAuthorize("@ss.hasPermission('system:tenant-package:create')")
    public Result<?> createTenantPackage() {
        return Result.ok();
    }
}
