package com.zp.gmall.module.trade.order.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单项持久化对象（PO）。
 * 与数据库 trade_order_item 表一一映射。
 */
@Data
@TableName("trade_order_item")
public class OrderItemPO {

    /** 订单项ID（主键） */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /** 所属订单ID */
    private Long orderId;

    /** 商品SKU ID */
    private Long skuId;

    /** 商品名称（快照） */
    private String productName;

    /** 商品图片 */
    private String productImage;

    /** 购买数量 */
    private Integer quantity;

    /** 单价 */
    private BigDecimal unitPrice;

    /** 小计 */
    private BigDecimal subtotal;

    /** 创建时间 */
    private LocalDateTime createTime;
}
