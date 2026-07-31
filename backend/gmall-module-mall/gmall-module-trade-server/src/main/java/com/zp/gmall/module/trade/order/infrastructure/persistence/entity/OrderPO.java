package com.zp.gmall.module.trade.order.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单持久化对象（PO）。
 * 与数据库 trade_order 表一一映射，由 MyBatis-Plus 操作。
 * 只包含数据字段，不含任何业务逻辑，领域对象与此 PO 通过 Converter 互相转换。
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("trade_order")
public class OrderPO extends BaseDO {

    /** 订单ID（主键） */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /** 订单编号（业务号） */
    private String orderNo;

    /** 会员ID */
    private Long memberId;

    /** 订单状态 */
    private String status;

    // --- 收货地址（平铺存储） ---
    private String receiverProvince;
    private String receiverCity;
    private String receiverRegion;
    private String receiverDetail;
    private String receiverPhone;

    // --- 金额（以分为单位或 BigDecimal） ---
    private BigDecimal totalAmount;
    private BigDecimal freightAmount;
    private BigDecimal payAmount;
    private BigDecimal discountAmount;

    // --- 支付信息 ---
    private LocalDateTime paidTime;
    private String paymentNo;

    // --- 物流信息 ---
    private LocalDateTime shippedTime;
    private String trackingNo;
    private String logisticsCompany;

    /** 订单备注 */
    private String remark;

    /** 版本号（乐观锁） */
    private Integer version;
}
