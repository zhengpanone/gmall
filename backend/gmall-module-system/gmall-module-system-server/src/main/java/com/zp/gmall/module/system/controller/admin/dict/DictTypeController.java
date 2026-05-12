package com.zp.gmall.module.system.controller.admin.dict;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictTypeDTO;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictTypePageDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictTypeVO;
import com.zp.gmall.module.system.service.dict.IDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "管理后台 - 字典类型管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/dict/type")
public class DictTypeController {
    private final IDictTypeService dictTYpeService;

    // 字典相关接口
    @PostMapping("/create")
    @Operation(summary = "创建字典类型")
    public Result<?> create(@Valid @RequestBody DictTypeDTO dictDTO) {
        return Result.ok(dictTYpeService.createDictType(dictDTO));
    }

    @PostMapping("/update")
    @Operation(summary = "更新字典")
    public Result<?> updateById(@Valid @RequestBody DictTypeDTO dictDTO) {
        dictTYpeService.updateDict(dictDTO);
        return Result.ok();
    }

    @PostMapping("/delete")
    @Operation(summary = "删除字典")
    public Result<?> deleteDictTyp(@Valid @RequestBody Ids ids) {
        dictTYpeService.deleteDict(ids);
        return Result.ok();
    }


    @GetMapping("/page")
    @Operation(summary = "获取字典分页")
    public PageResult<DictTypeVO> getDictTypPage(@Valid DictTypePageDTO typePageDTO) {
        return dictTYpeService.getDictPage(typePageDTO);
    }

    @GetMapping("/get")
    @Operation(summary = "获取字典详情")
    public Result<DictTypeVO> getDictTypById(
            @Parameter(description = "字典ID", required = true, example = "1")
            @RequestParam("id") String id) {
        return Result.ok(dictTYpeService.getDictById(id));
    }
}
