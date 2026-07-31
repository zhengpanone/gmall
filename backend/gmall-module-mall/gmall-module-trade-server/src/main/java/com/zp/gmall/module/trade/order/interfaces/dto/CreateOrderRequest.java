package com.zp.gmall.module.trade.order.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 创建订单请求 DTO（接口层入参）。
 * 使用 Jakarta Validation 进行基本格式校验，
 * 核心业务校验由领域层负责。
 */
@Schema(description = "创建订单请求")
public class CreateOrderRequest {

    @Schema(description = "会员ID", required = true, example = "1001")
    @NotNull(message = "会员ID不能为空")
    private Long memberId;

    @Schema(description = "订单项列表", required = true)
    @NotEmpty(message = "订单项不能为空")
    private List<OrderItemRequest> items;

    @Schema(description = "收货人姓名", example = "张三")
    private String receiverName;

    @Schema(description = "收货人手机号", example = "13800138000")
    private String receiverPhone;

    @Schema(description = "省份", example = "广东省")
    private String receiverProvince;

    @Schema(description = "城市", example = "深圳市")
    private String receiverCity;

    @Schema(description = "区/县", example = "南山区")
    private String receiverRegion;

    @Schema(description = "详细地址", example = "科技园路1号")
    private String receiverDetail;

    @Schema(description = "运费", example = "0.00")
    private String freightAmount;

    @Schema(description = "优惠金额", example = "5.00")
    private String discountAmount;

    @Schema(description = "订单备注")
    private String remark;

    @Schema(description = "订单项")
    public static class OrderItemRequest {
        @Schema(description = "SKU ID", required = true)
        @NotNull
        private Long skuId;

        @Schema(description = "商品名称", required = true)
        private String productName;

        @Schema(description = "商品图片")
        private String productImage;

        @Schema(description = "数量", required = true)
        @NotNull
        private Integer quantity;

        @Schema(description = "单价", required = true)
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
    public List<OrderItemRequest> getItems() { return items; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }
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
