package com.zp.gmall.module.system.controller.admin.dict;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictDataDTO;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictDataQueryDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictDataVO;
import com.zp.gmall.module.system.service.dict.IDictDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "管理后台 - 字典数据管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/dict/data")
public class DictDataController {
    private final IDictDataService dictTYpeService;

    // 字典相关接口
    @PostMapping("/create")
    @Operation(summary = "创建字典数据")
    public Result<?> createDict(@Valid @RequestBody DictDataDTO dictDTO) {
        dictTYpeService.createDictData(dictDTO);
        return Result.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "更新字典")
    public Result<?> updateDict(@Valid @RequestBody DictDataDTO dictDTO) {
        dictTYpeService.updateDictData(dictDTO);
        return Result.ok();
    }

    @PostMapping("/delete")
    @Operation(summary = "删除字典")
    public Result<?> deleteDict(@Valid @RequestBody Ids ids) {
        dictTYpeService.deleteDictData(ids);
        return Result.ok();
    }


    @GetMapping("/list")
    @Operation(summary = "获取字典分页")
    public Result<List<DictDataVO>> getDictList(@Valid DictDataQueryDTO dataQueryDTO) {
        return dictTYpeService.getDictDataList(dataQueryDTO);
    }

    @GetMapping("/get")
    @Operation(summary = "获取字典详情")
    public Result<DictDataVO> getDictById(
            @Parameter(description = "字典ID", required = true, example = "1")
            @RequestParam("id") String id) {
        return Result.ok(dictTYpeService.getDictDataById(id));
    }
}
