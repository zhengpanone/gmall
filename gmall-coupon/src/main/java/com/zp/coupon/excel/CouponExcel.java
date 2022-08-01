package com.zp.coupon.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券信息
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
public class CouponExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]")
    private Integer couponType;
    @Excel(name = "优惠券图片")
    private String couponImg;
    @Excel(name = "优惠卷名字")
    private String couponName;
    @Excel(name = "数量")
    private Integer num;
    @Excel(name = "金额")
    private BigDecimal amount;
    @Excel(name = "每人限领张数")
    private Integer perLimit;
    @Excel(name = "使用门槛")
    private BigDecimal minPoint;
    @Excel(name = "开始时间")
    private Date startTime;
    @Excel(name = "结束时间")
    private Date endTime;
    @Excel(name = "使用类型[0->全场通用；1->指定分类；2->指定商品]")
    private Integer useType;
    @Excel(name = "备注")
    private String note;
    @Excel(name = "发行数量")
    private Integer publishCount;
    @Excel(name = "已使用数量")
    private Integer useCount;
    @Excel(name = "领取数量")
    private Integer receiveCount;
    @Excel(name = "可以领取的开始日期")
    private Date enableStartTime;
    @Excel(name = "可以领取的结束日期")
    private Date enableEndTime;
    @Excel(name = "优惠码")
    private String code;
    @Excel(name = "可以领取的会员等级[0->不限等级，其他-对应等级]")
    private Integer memberLevel;
    @Excel(name = "发布状态[0-未发布，1-已发布]")
    private Integer publish;

}