package com.zp.coupon.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 秒杀活动
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
public class SeckillPromotionExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "活动标题")
    private String title;
    @Excel(name = "开始日期")
    private Date startTime;
    @Excel(name = "结束日期")
    private Date endTime;
    @Excel(name = "上下线状态")
    private Integer status;
    @Excel(name = "创建时间")
    private Date createTime;
    @Excel(name = "创建人")
    private String userId;

}