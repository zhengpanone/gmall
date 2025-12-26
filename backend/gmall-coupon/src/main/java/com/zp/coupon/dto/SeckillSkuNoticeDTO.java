package com.zp.coupon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 秒杀商品通知订阅
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
@ApiModel(value = "秒杀商品通知订阅")
public class SeckillSkuNoticeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "member_id")
	private Long memberId;

	@ApiModelProperty(value = "sku_id")
	private Long skuId;

	@ApiModelProperty(value = "活动场次id")
	private Long sessionId;

	@ApiModelProperty(value = "订阅时间")
	private Date subcribeTime;

	@ApiModelProperty(value = "发送时间")
	private Date sendTime;

	@ApiModelProperty(value = "通知方式[0-短信，1-邮件]")
	private Integer noticeType;


}