package com.zp.gmall.module.erp.controller.admin.product;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.erp.controller.admin.product.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.erp.controller.admin.product
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */

@Tag(name = "管理后台 - ERP 产品")
@RestController
@RequestMapping("/product")
@Validated
public class ErpProductController {

    @PostMapping("/create")
    @Operation(summary = "创建产品")
    public Result<?> createProduct(@Valid @RequestBody ProductDTO productDTO) {
//        productService.createProduct(createReqVO);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品")
    public Result<?> updateProduct(@Valid @RequestBody ProductDTO productDTO) {
//        productService.updateProduct(updateReqVO);
        return Result.ok();
    }
}
