package com.zp.gmall.module.trade.order.application.command;

import java.util.List;

/**
 * 创建订单命令对象（应用层 DTO）。
 * 由接口层传入，携带创建订单所需的外部数据。
 */
public class CreateOrderCommand {

    /** 会员ID */
    private Long memberId;

    /** 订单项 */
    private List<OrderItemCommand> items;

    /** 收货人姓名 */
    private String receiverName;

    /** 收货人手机号 */
    private String receiverPhone;

    /** 收货地址 - 省 */
    private String receiverProvince;

    /** 收货地址 - 市 */
    private String receiverCity;

    /** 收货地址 - 区 */
    private String receiverRegion;

    /** 收货地址 - 详情 */
    private String receiverDetail;

    /** 运费 */
    private String freightAmount;

    /** 优惠金额 */
    private String discountAmount;

    /** 订单备注 */
    private String remark;

    /**
     * 订单项命令
     */
    public static class OrderItemCommand {
        private Long skuId;
        private String productName;
        private String productImage;
        private Integer quantity;
        private String unitPrice;

        public Long getSkuId() { return skuId; }
        public void setSkuId(Long skuId) { this.skuId = skuId; }
        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }
        public String getProductImage() { return productImage; }
        public void setProductImage(String productImage) { this.productImage = productImage; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
        public String getUnitPrice() { return unitPrice; }
        public void setUnitPrice(String unitPrice) { this.unitPrice = unitPrice; }
    }

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }
    public List<OrderItemCommand> getItems() { return items; }
    public void setItems(List<OrderItemCommand> items) { this.items = items; }
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public String getReceiverPhone() { return receiverPhone; }
    public void setReceiverPhone(String receiverPhone) { this.receiverPhone = receiverPhone; }
    public String getReceiverProvince() { return receiverProvince; }
    public void setReceiverProvince(String receiverProvince) { this.receiverProvince = receiverProvince; }
    public String getReceiverCity() { return receiverCity; }
    public void setReceiverCity(String receiverCity) { this.receiverCity = receiverCity; }
    public String getReceiverRegion() { return receiverRegion; }
    public void setReceiverRegion(String receiverRegion) { this.receiverRegion = receiverRegion; }
    public String getReceiverDetail() { return receiverDetail; }
    public void setReceiverDetail(String receiverDetail) { this.receiverDetail = receiverDetail; }
    public String getFreightAmount() { return freightAmount; }
    public void setFreightAmount(String freightAmount) { this.freightAmount = freightAmount; }
    public String getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(String discountAmount) { this.discountAmount = discountAmount; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}
