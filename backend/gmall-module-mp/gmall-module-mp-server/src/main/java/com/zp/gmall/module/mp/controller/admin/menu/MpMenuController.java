package com.zp.gmall.module.mp.controller.admin.menu;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.mp.controller.admin.menu.dto.MpMenuDTO;
import com.zp.gmall.module.mp.controller.admin.menu.vo.MpMenuVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:29
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "管理后台 - 公众号菜单")
@RestController
@RequestMapping("/menu")
@Validated
public class MpMenuController {

    @PostMapping("/save")
    @Operation(summary = "保存公众号菜单")
    public Result<?> saveMenu(@Valid @RequestBody MpMenuDTO mpMenuDTO) {
        //mpMenuService.saveMenu(createReqVO);
        return Result.ok();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公众号菜单")
    @Parameter(name = "accountId", description = "公众号账号的编号", required = true, example = "10")
    public Result<?> deleteMenu(@RequestParam("accountId") Long accountId) {
        //mpMenuService.deleteMenuByAccountId(accountId);
        return Result.ok();
    }

    @GetMapping("/list")
    @Operation(summary = "获得公众号菜单列表")
    @Parameter(name = "accountId", description = "公众号账号的编号", required = true, example = "10")
    public Result<List<MpMenuVO>> getMenuList(@RequestParam("accountId") Long accountId) {
        //List<MpMenuDO> list = mpMenuService.getMenuListByAccountId(accountId);
        //MpMenuConvert.INSTANCE.convertList(list)
        return Result.ok(null);
    }
}
