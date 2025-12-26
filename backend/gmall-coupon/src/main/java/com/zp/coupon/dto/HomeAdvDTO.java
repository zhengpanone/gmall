package com.zp.coupon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 首页轮播广告
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
@ApiModel(value = "首页轮播广告")
public class HomeAdvDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "名字")
	private String name;

	@ApiModelProperty(value = "图片地址")
	private String pic;

	@ApiModelProperty(value = "开始时间")
	private Date startTime;

	@ApiModelProperty(value = "结束时间")
	private Date endTime;

	@ApiModelProperty(value = "状态")
	private Integer status;

	@ApiModelProperty(value = "点击数")
	private Integer clickCount;

	@ApiModelProperty(value = "广告详情连接地址")
	private String url;

	@ApiModelProperty(value = "备注")
	private String note;

	@ApiModelProperty(value = "排序")
	private Integer sort;

	@ApiModelProperty(value = "发布者")
	private Long publisherId;

	@ApiModelProperty(value = "审核者")
	private Long authId;


}