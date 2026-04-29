package com.zp.gmall.module.product.controller.admin.brand;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.product.controller.admin.brand.dto.ProductBrandDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:49
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "管理后台 - 商品品牌")
@RestController
@RequestMapping("/brand")
@Validated
public class ProductBrandController {
    @PostMapping("/create")
    @Operation(summary = "创建品牌")
    public Result<?> createBrand(@Valid @RequestBody ProductBrandDTO productBrandDTO) {
        //brandService.createBrand(createReqVO);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新品牌")
    public Result<?> updateBrand(@Valid @RequestBody ProductBrandDTO productBrandDTO) {
        //brandService.updateBrand(updateReqVO);
        return Result.ok();
    }
}
