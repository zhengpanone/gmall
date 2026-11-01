package com.zp.gmall.module.product.controller.admin.category;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.product.controller.admin.category.dto.ProductCategoryDTO;
import com.zp.gmall.module.product.controller.admin.category.dto.ProductCategoryPageDTO;
import com.zp.gmall.module.product.controller.admin.category.vo.ProductCategoryVO;
import com.zp.gmall.module.product.service.category.IProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:57
 * Version : v1.0.0
 * Description: 商品分类管理控制器
 */
@Tag(name = "管理后台 - 商品分类")
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Validated
public class ProductCategoryController {

    private final IProductCategoryService categoryService;

    /**
     * 创建商品分类
     *
     * @param productCategoryDTO 商品分类数据传输对象，包含分类的详细信息
     * @return 操作结果，表示分类创建成功
     */
    @PostMapping("/create")
    @Operation(summary = "创建商品分类")
    public Result<?> create(@Valid @RequestBody ProductCategoryDTO productCategoryDTO) {
        categoryService.create(productCategoryDTO);
        return Result.ok();
    }

    /**
     * 更新商品分类信息
     *
     * @param productCategoryDTO 商品分类数据传输对象，包含需要更新的分类信息
     * @return 操作结果，成功时返回成功状态
     */
    @PutMapping("/update")
    @Operation(summary = "更新商品分类")
    public Result<?> update(@Valid @RequestBody ProductCategoryDTO productCategoryDTO) {
        categoryService.update(productCategoryDTO);
        return Result.ok();
    }

    /**
     * 删除商品分类
     *
     * @param ids 待删除的商品分类ID列表，包含需要删除的分类标识
     * @return 操作成功返回成功结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除商品分类")
    public Result<?> delete(@Valid @RequestBody Ids ids) {
        categoryService.delete(ids);
        return Result.ok();
    }

    @GetMapping("/get")
    @Operation(summary = "获取分类详情")
    public Result<ProductCategoryVO> getById(
            @Parameter(description = "分类ID", required = true, example = "1")
            @RequestParam("id") String id) {
        return Result.ok(categoryService.getById(id));
    }

    /**
     * 获取商品分类分页列表
     *
     * @param productCategoryPageDTO 商品分类分页查询条件
     * @return 商品分类分页结果
     */
    @GetMapping("/page")
    @Operation(summary = "获取商品分类分页")
    public PageResult<ProductCategoryVO> getPage(@Valid ProductCategoryPageDTO productCategoryPageDTO) {
        return categoryService.getPage(productCategoryPageDTO);
    }
}
