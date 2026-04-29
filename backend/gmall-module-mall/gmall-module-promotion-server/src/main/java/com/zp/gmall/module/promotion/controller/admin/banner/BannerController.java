package com.zp.gmall.module.promotion.controller.admin.banner;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.promotion.controller.admin.banner.dto.BannerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 23:24
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "管理后台 - Banner 管理")
@RestController
@RequestMapping("/banner")
@Validated
public class BannerController {
    @PostMapping("/create")
    @Operation(summary = "创建 Banner")
    public Result<?> createBanner(@Valid @RequestBody BannerDTO bannerDTO) {
        //bannerService.createBanner(createReqVO);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新 Banner")
    public Result<?> updateBanner(@Valid @RequestBody BannerDTO bannerDTO) {
        //bannerService.updateBanner(updateReqVO);
        return  Result.ok();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除 Banner")
    @Parameter(name = "id", description = "编号", required = true)
    public Result<?> deleteBanner(@RequestParam("id") Long id) {
        //bannerService.deleteBanner(id);
        return  Result.ok();
    }
}
