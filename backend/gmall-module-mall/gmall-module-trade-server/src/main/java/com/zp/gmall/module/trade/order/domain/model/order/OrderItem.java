package com.zp.gmall.module.trade.order.domain.model.order;

import com.zp.gmall.framework.common.ddd.BaseEntity;
import com.zp.gmall.module.trade.order.domain.model.valueobject.Money;

/**
 * 订单项 - 实体。
 * 属于订单聚合内部，通过订单聚合根访问，不可独立存在。
 */
public class OrderItem extends BaseEntity<OrderItemId> {

    /** 订单项标识 */
    private OrderItemId id;

    /** 所属订单ID */
    private OrderId orderId;

    /** 商品SKU ID */
    private Long skuId;

    /** 商品名称（冗余，快照） */
    private String productName;

    /** 商品图片 */
    private String productImage;

    /** 购买数量 */
    private Integer quantity;

    /** 单价 */
    private Money unitPrice;

    /** 小计金额 = 单价 * 数量 */
    private Money subtotal;

    // 框架需要无参构造（用于反射/反序列化），设为 protected
    protected OrderItem() {
    }

    /**
     * 创建订单项
     */
    public OrderItem(OrderItemId id, OrderId orderId, Long skuId,
                     String productName, String productImage,
                     Integer quantity, Money unitPrice) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("购买数量必须大于0");
        }
        if (unitPrice == null) {
            throw new IllegalArgumentException("单价不能为空");
        }
        this.id = id;
        this.orderId = orderId;
        this.skuId = skuId;
        this.productName = productName;
        this.productImage = productImage;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = unitPrice.multiply(quantity);
    }

    /**
     * 修改数量并重新计算小计
     */
    public void changeQuantity(Integer newQuantity) {
        if (newQuantity == null || newQuantity <= 0) {
            throw new IllegalArgumentException("购买数量必须大于0");
        }
        this.quantity = newQuantity;
        this.subtotal = this.unitPrice.multiply(newQuantity);
    }

    @Override
    public OrderItemId getId() {
        return id;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public Money getSubtotal() {
        return subtotal;
    }
}
