package com.zp.module.system.controller.admin.tenant;

import com.zp.framework.apilog.core.annotation.ApiAccessLog;
import com.zp.framework.common.pojo.PageParam;
import com.zp.framework.common.pojo.PageResult;
import com.zp.framework.common.pojo.Result;
import com.zp.framework.common.util.object.BeanUtils;
import com.zp.framework.excel.core.util.ExcelUtils;
import com.zp.module.system.controller.admin.tenant.dto.TenantPageDTO;
import com.zp.module.system.controller.admin.tenant.dto.TenantSaveDTO;
import com.zp.module.system.controller.admin.tenant.vo.TenantSimpleVO;
import com.zp.module.system.controller.admin.tenant.vo.TenantVO;
import com.zp.module.system.dal.dataobject.tenant.TenantDO;
import com.zp.module.system.service.tenant.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.zp.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static com.zp.framework.common.pojo.Result.ok;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 11:33
 * Version : v1.0.0
 * Description: 租户
 */
@Tag(name = "管理后台 - 租户")
@RestController
@RequestMapping("/system/tenant")
public class TenantController {

    @Resource
    private TenantService tenantService;


    @GetMapping("/get-by-website")
    @PermitAll
    @Operation(summary = "使用域名，获得租户信息", description = "登录界面，根据用户的域名，获得租户信息")
    @Parameter(name = "website", description = "域名", required = true, example = "www.gmall.cn")
    public Result<TenantSimpleVO> getTenantByWebsite(@RequestParam("website") String website) {
        TenantDO tenant = tenantService.getTenantByWebsite(website);
        return ok(BeanUtils.toBean(tenant, TenantSimpleVO.class));
    }

    @GetMapping("/get-id-by-name")
    @PermitAll
    @Operation(summary = "使用租户名，获得租户编号", description = "登录界面，根据用户的租户名，获得租户编号")
    @Parameter(name = "name", description = "租户名", required = true, example = "1024")
    public Result<String> getTenantIdByName(@RequestParam("name") String name) {
        TenantDO tenant = tenantService.getTenantByName(name);
        return ok(tenant != null ? tenant.getId() : null);
    }


    @PostMapping("/create")
    @Operation(summary = "创建租户")
    @PreAuthorize("@ss.hasPermission('system:tenant:create')")
    public Result<String> createTenant(@Valid @RequestBody TenantSaveDTO createDTO) {
        return ok(tenantService.createTenant(createDTO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新租户")
    @PreAuthorize("@ss.hasPermission('system:tenant:update')")
    public Result<Boolean> updateTenant(@Valid @RequestBody TenantSaveDTO updateDTO) {
        tenantService.updateTenant(updateDTO);
        return ok(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除租户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:tenant:delete')")
    public Result<Boolean> deleteTenant(@RequestParam("id") String id) {
        tenantService.deleteTenant(id);
        return ok(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得租户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:tenant:query')")
    public Result<TenantVO> getTenant(@RequestParam("id") String id) {
        TenantDO tenant = tenantService.getTenant(id);
        return ok(BeanUtils.toBean(tenant, TenantVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得租户分页")
    @PreAuthorize("@ss.hasPermission('system:tenant:query')")
    public Result<PageResult<TenantVO>> getTenantPage(@Valid TenantPageDTO pageDTO) {
        PageResult<TenantDO> pageResult = tenantService.getTenantPage(pageDTO);
        return ok(BeanUtils.toBean(pageResult, TenantVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出租户 Excel")
    @PreAuthorize("@ss.hasPermission('system:tenant:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTenantExcel(@Valid TenantPageDTO exportDTO,
                                  HttpServletResponse response) throws IOException {
        exportDTO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TenantDO> list = tenantService.getTenantPage(exportDTO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "租户.xls", "数据", TenantVO.class,
                BeanUtils.toBean(list, TenantVO.class));
    }
}
