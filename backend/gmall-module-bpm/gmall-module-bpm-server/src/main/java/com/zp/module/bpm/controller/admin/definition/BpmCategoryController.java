package com.zp.module.bpm.controller.admin.definition;

import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.module.bpm.controller.admin.definition.dto.BpmCategoryDTO;
import com.zp.module.bpm.controller.admin.definition.dto.BpmCategoryPageDTO;
import com.zp.module.bpm.controller.admin.definition.vo.BpmCategoryVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理后台 - 流程分类")
@RestController
@RequestMapping("/category")
public class BpmCategoryController {

    @PostMapping("/create")
    @Operation(summary = "创建流程分类")
    public Result<?> createCategory(@Valid @RequestBody BpmCategoryDTO bpmCategoryDTO) {
//        categoryService.createCategory(createReqVO);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新流程分类")
    public Result<?> updateCategory(@Valid @RequestBody BpmCategoryDTO updateReqVO) {
//        categoryService.updateCategory(updateReqVO);
        return Result.ok();
    }


    @DeleteMapping("/delete")
    @Operation(summary = "删除流程分类")
    @Parameter(name = "id", description = "编号", required = true)
    public Result<?> deleteCategory(@RequestParam("id") Long id) {
//        categoryService.deleteCategory(id);
        return Result.ok();
    }

    @GetMapping("/get")
    @Operation(summary = "获得流程分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public Result<BpmCategoryVO> getCategory(@RequestParam("id") Long id) {
//        BpmCategoryDO category = categoryService.getCategory(id);
        return Result.ok(null);
    }

    @GetMapping("/page")
    @Operation(summary = "获得流程分类分页")
    public PageResult<BpmCategoryVO> getCategoryPage(@Valid BpmCategoryPageDTO pageDTO) {
//        PageResult<BpmCategoryDO> pageResult = categoryService.getCategoryPage(pageReqVO);
        return PageResult.ok(1L, null);
    }

}
