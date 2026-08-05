package com.zp.gmall.module.infra.controller.admin.codegen;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.infra.convert.codegen.CodegenConvert;
import com.zp.gmall.module.infra.service.codegen.ICodegenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.zp.gmall.framework.common.domain.vo.Result.ok;

@Tag(name = "管理后台 - 代码生成器")
@RestController
@RequestMapping("/infra/codegen")
@Validated
public class CodegenController {
    @Resource
    private ICodegenService codegenService;

    @Operation(summary = "预览生成代码")
    @GetMapping("/preview")
    @Parameter(name = "tableId", description = "表编号", required = true, example = "1024")
    public Result<?> previewCodegen(@RequestParam("tableId") String tableId) {
        Map<String, String> codes = codegenService.generationCodes(tableId);
        return ok(CodegenConvert.INSTANCE.convert(codes));
    }
}
