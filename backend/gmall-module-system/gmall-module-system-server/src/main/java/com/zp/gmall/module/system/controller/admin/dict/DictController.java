package com.zp.gmall.module.system.controller.admin.dict;

import com.zp.gmall.framework.common.pojo.Result;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictSaveDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictVO;
import com.zp.gmall.module.system.service.dict.IDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "管理后台 - 字典管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/dict")
public class DictController {
    private final IDictService dictService;

    // 字典相关接口
    @PostMapping("/create")
    @Operation(summary = "创建字典")
    public Result<DictVO> createDict(@Valid @RequestBody DictSaveDTO dictDTO) {
        DictVO dictVO = dictService.createDict(dictDTO);
        return Result.ok(dictVO);
    }
}
