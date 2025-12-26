package com.zp.coupon.controller;

import com.zp.common.annotation.LogOperation;
import com.zp.common.constant.Constant;
import com.zp.common.page.PageData;
import com.zp.common.utils.ExcelUtils;
import com.zp.common.utils.Result;
import com.zp.common.validator.AssertUtils;
import com.zp.common.validator.ValidatorUtils;
import com.zp.common.validator.group.AddGroup;
import com.zp.common.validator.group.DefaultGroup;
import com.zp.common.validator.group.UpdateGroup;
import com.zp.coupon.dto.SkuFullReductionDTO;
import com.zp.coupon.excel.SkuFullReductionExcel;
import com.zp.coupon.service.SkuFullReductionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 商品满减信息
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@RestController
@RequestMapping("coupon/skufullreduction")
@Api(tags="商品满减信息")
public class SkuFullReductionController {
    @Autowired
    private SkuFullReductionService skuFullReductionService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<PageData<SkuFullReductionDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<SkuFullReductionDTO> page = skuFullReductionService.page(params);

        return new Result<PageData<SkuFullReductionDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<SkuFullReductionDTO> get(@PathVariable("id") Long id){
        SkuFullReductionDTO data = skuFullReductionService.get(id);

        return new Result<SkuFullReductionDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody SkuFullReductionDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        skuFullReductionService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result update(@RequestBody SkuFullReductionDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        skuFullReductionService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        skuFullReductionService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SkuFullReductionDTO> list = skuFullReductionService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, SkuFullReductionExcel.class);
    }

}