package com.zp.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 商品评价
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Data
@ApiModel(value = "商品评价")
public class SpuCommentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "sku_id")
	private Long skuId;

	@ApiModelProperty(value = "spu_id")
	private Long spuId;

	@ApiModelProperty(value = "商品名字")
	private String spuName;

	@ApiModelProperty(value = "会员昵称")
	private String memberNickName;

	@ApiModelProperty(value = "星级")
	private Integer star;

	@ApiModelProperty(value = "会员ip")
	private String memberIp;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "显示状态[0-不显示，1-显示]")
	private Integer showStatus;

	@ApiModelProperty(value = "购买时属性组合")
	private String spuAttributes;

	@ApiModelProperty(value = "点赞数")
	private Integer likesCount;

	@ApiModelProperty(value = "回复数")
	private Integer replyCount;

	@ApiModelProperty(value = "评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]")
	private String resources;

	@ApiModelProperty(value = "内容")
	private String content;

	@ApiModelProperty(value = "用户头像")
	private String memberIcon;

	@ApiModelProperty(value = "评论类型[0 - 对商品的直接评论，1 - 对评论的回复]")
	private Integer commentType;


}