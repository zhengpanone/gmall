package com.zp.gmall.module.product.controller.admin.brand;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.product.controller.admin.brand.dto.ProductBrandDTO;
import com.zp.gmall.module.product.controller.admin.brand.dto.ProductBrandPageDTO;
import com.zp.gmall.module.product.controller.admin.brand.vo.ProductBrandVO;
import com.zp.gmall.module.product.service.brand.IProductBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Validated
public class ProductBrandController {

    private final IProductBrandService brandService;

    @PostMapping("/create")
    @Operation(summary = "创建品牌")
    public Result<?> create(@Valid @RequestBody ProductBrandDTO productBrandDTO) {
        brandService.create(productBrandDTO);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新品牌")
    public Result<?> update(@Valid @RequestBody ProductBrandDTO productBrandDTO) {
        brandService.update(productBrandDTO);
        return Result.ok();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除品牌")
    public Result<?> delete(@Valid @RequestBody Ids ids) {
        brandService.delete(ids);
        return Result.ok();
    }

    @GetMapping("/page")
    @Operation(summary = "获取品牌分页")
    public PageResult<ProductBrandVO> getPage(@Valid ProductBrandPageDTO productBrandPageDTO) {
        return brandService.getPage(productBrandPageDTO);
    }

    @GetMapping("/get")
    @Operation(summary = "获取品牌详情")
    public Result<ProductBrandVO> getById(
            @Parameter(description = "品牌ID", required = true, example = "1")
            @RequestParam("id") String id) {
        return Result.ok(brandService.getById(id));
    }
}
