package com.zp.gmall.module.system.controller.admin.dict;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictDTO;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictPageDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictVO;
import com.zp.gmall.module.system.service.dict.IDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "管理后台 - 字典管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/dict")
public class DictController {
    private final IDictService dictService;

    // 字典相关接口
    @PostMapping("/create")
    @Operation(summary = "创建字典")
    public Result<?> createDict(@Valid @RequestBody DictDTO dictDTO) {
       dictService.createDict(dictDTO);
        return Result.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "更新字典")
    public Result<?> updateDict(@Valid @RequestBody DictDTO dictDTO) {
        dictService.updateDict(dictDTO);
        return Result.ok();
    }

    @PostMapping("/delete")
    @Operation(summary = "删除字典")
    public Result<?> deleteDict(@Valid @RequestBody Ids ids) {
        dictService.deleteDict(ids);
        return Result.ok();
    }


    @GetMapping("/page")
    @Operation(summary = "获取字典分页")
    public PageResult<DictVO> getDictPage(@Valid DictPageDTO dictPageDTO) {
        return dictService.getDictPage(dictPageDTO);
    }

    @GetMapping("/get")
    @Operation(summary = "获取字典详情")
    public Result<DictVO> getDictById(
            @Parameter(description = "字典ID", required = true, example = "1")
            @RequestParam("id") String id) {
        return Result.ok(dictService.getDictById(id));
    }
}
