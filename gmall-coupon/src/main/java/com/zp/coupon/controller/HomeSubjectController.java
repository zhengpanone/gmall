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
import com.zp.coupon.dto.HomeSubjectDTO;
import com.zp.coupon.excel.HomeSubjectExcel;
import com.zp.coupon.service.HomeSubjectService;
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
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@RestController
@RequestMapping("coupon/homesubject")
@Api(tags="首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】")
public class HomeSubjectController {
    @Autowired
    private HomeSubjectService homeSubjectService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<PageData<HomeSubjectDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<HomeSubjectDTO> page = homeSubjectService.page(params);

        return new Result<PageData<HomeSubjectDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<HomeSubjectDTO> get(@PathVariable("id") Long id){
        HomeSubjectDTO data = homeSubjectService.get(id);

        return new Result<HomeSubjectDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody HomeSubjectDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        homeSubjectService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result update(@RequestBody HomeSubjectDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        homeSubjectService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        homeSubjectService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<HomeSubjectDTO> list = homeSubjectService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, HomeSubjectExcel.class);
    }

}