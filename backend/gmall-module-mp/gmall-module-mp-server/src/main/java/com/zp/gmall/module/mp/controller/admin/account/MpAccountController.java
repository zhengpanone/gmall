package com.zp.gmall.module.mp.controller.admin.account;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.mp.controller.admin.account.dto.MpAccountDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:25
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "管理后台 - 公众号账号")
@RestController
@RequestMapping("/account")
@Validated
public class MpAccountController {

    @PostMapping("/create")
    @Operation(summary = "创建公众号账号")
    public Result<?> createAccount(@Valid @RequestBody MpAccountDTO mpAccountDTO) {
        //mpAccountService.createAccount(createReqVO);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新公众号账号")
    public Result<?> updateAccount(@Valid @RequestBody MpAccountDTO mpAccountDTO) {
        //mpAccountService.updateAccount(updateReqVO);
        return Result.ok();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公众号账号")
    @Parameter(name = "id", description = "编号", required = true)
    public Result<?> deleteAccount(@RequestParam("id") Long id) {
        //mpAccountService.deleteAccount(id);
        return Result.ok();
    }
}
