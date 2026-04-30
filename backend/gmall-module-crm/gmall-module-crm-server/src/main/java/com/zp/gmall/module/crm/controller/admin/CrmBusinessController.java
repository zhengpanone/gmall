package com.zp.gmall.module.crm.controller.admin;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.crm.controller.admin.dto.CrmBusinessDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.crm.controller.admin
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */

@Tag(name = "管理后台 - CRM 商机")
@RestController
@RequestMapping("/business")
@Validated
public class CrmBusinessController {

    @PostMapping("/create")
    @Operation(summary = "创建商机")
    public Result<?> createBusiness(@Valid @RequestBody CrmBusinessDTO crmBusinessDTO) {
//        businessService.createBusiness(createReqVO, getLoginUserId());
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新商机")
    public Result<?> updateBusiness(@Valid @RequestBody CrmBusinessDTO crmBusinessDTO) {
//        businessService.updateBusiness(updateReqVO);
        return Result.ok();
    }

    @PutMapping("/update-status")
    @Operation(summary = "更新商机状态")
    public Result<?> updateBusinessStatus(@Valid @RequestBody CrmBusinessDTO updateStatusReqVO) {
//        businessService.updateBusinessStatus(updateStatusReqVO);
        return Result.ok();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除商机")
    @Parameter(name = "id", description = "编号", required = true)
    public Result<?> deleteBusiness(@RequestParam("id") Long id) {
//        businessService.deleteBusiness(id);
        return Result.ok();
    }
}
