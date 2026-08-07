package com.zp.module.bpm.controller.admin.definition;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.module.bpm.controller.admin.definition.dto.BpmCategoryCreateDTO;
import com.zp.module.bpm.controller.admin.definition.dto.BpmCategoryPageDTO;
import com.zp.module.bpm.controller.admin.definition.dto.BpmCategoryUpdateDTO;
import com.zp.module.bpm.controller.admin.definition.vo.BpmCategoryVO;
import com.zp.module.bpm.service.definition.IBpmCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理后台 - 流程分类")
@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class BpmCategoryController {

    private final IBpmCategoryService categoryService;

    @PostMapping("/create")
    @Operation(summary = "创建流程分类")
    public Result<?> createCategory(@Valid @RequestBody BpmCategoryCreateDTO createDTO) {
        categoryService.create(createDTO);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新流程分类")
    public Result<?> updateCategory(@Valid @RequestBody BpmCategoryUpdateDTO updateDTO) {
        categoryService.update(updateDTO);
        return Result.ok();
    }


    @DeleteMapping("/delete")
    @Operation(summary = "删除流程分类")
    @Parameter(name = "id", description = "编号", required = true)
    public Result<?> deleteCategory(@RequestBody @Valid Ids ids) {
        categoryService.deleteByIds(ids);
        return Result.ok();
    }

    @GetMapping("/get")
    @Operation(summary = "获得流程分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public Result<BpmCategoryVO> getCategory(@RequestParam("id") String id) {
        BpmCategoryVO category = categoryService.getById(id);
        return Result.ok(category);
    }

    @GetMapping("/page")
    @Operation(summary = "获得流程分类分页")
    public PageResult<BpmCategoryVO> getCategoryPage(@Valid BpmCategoryPageDTO pageDTO) {
        return categoryService.getPageList(pageDTO);
    }

}
