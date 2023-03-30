package com.zp.coupon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
@ApiModel(value = "首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】")
public class HomeSubjectDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "专题名字")
	private String name;

	@ApiModelProperty(value = "专题标题")
	private String title;

	@ApiModelProperty(value = "专题副标题")
	private String subTitle;

	@ApiModelProperty(value = "显示状态")
	private Integer status;

	@ApiModelProperty(value = "详情连接")
	private String url;

	@ApiModelProperty(value = "排序")
	private Integer sort;

	@ApiModelProperty(value = "专题图片地址")
	private String img;


}