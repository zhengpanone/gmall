package com.zp.coupon.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 秒杀商品通知订阅
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
public class SeckillSkuNoticeExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "member_id")
    private Long memberId;
    @Excel(name = "sku_id")
    private Long skuId;
    @Excel(name = "活动场次id")
    private Long sessionId;
    @Excel(name = "订阅时间")
    private Date subcribeTime;
    @Excel(name = "发送时间")
    private Date sendTime;
    @Excel(name = "通知方式[0-短信，1-邮件]")
    private Integer noticeType;

}