package com.zp.coupon.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
public class HomeSubjectExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "专题名字")
    private String name;
    @Excel(name = "专题标题")
    private String title;
    @Excel(name = "专题副标题")
    private String subTitle;
    @Excel(name = "显示状态")
    private Integer status;
    @Excel(name = "详情连接")
    private String url;
    @Excel(name = "排序")
    private Integer sort;
    @Excel(name = "专题图片地址")
    private String img;

}