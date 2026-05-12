package com.zp.gmall.module.product.controller.app.category;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.product.controller.admin.category.vo.ProductCategoryVO;
import com.zp.gmall.module.product.service.category.IProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:56
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "用户 APP-商品分类")
@RestController
@RequestMapping("/category")
@Validated
@RequiredArgsConstructor
public class AppCategoryController {

    @Resource
    private IProductCategoryService categoryService;

    @GetMapping("/list")
    @Operation(summary = "获取商品分类列表")
    public Result<?> getList(){
      List<ProductCategoryVO> list = categoryService.getList();
        return Result.ok(list);
    }

}
