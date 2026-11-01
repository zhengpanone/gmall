package com.zp.gmall.module.trade.order.infrastructure.persistence.converter;

import com.zp.gmall.module.trade.order.domain.model.order.Order;
import com.zp.gmall.module.trade.order.domain.model.order.OrderId;
import com.zp.gmall.module.trade.order.domain.model.order.OrderItem;
import com.zp.gmall.module.trade.order.domain.model.order.OrderItemId;
import com.zp.gmall.module.trade.order.domain.model.order.OrderStatus;
import com.zp.gmall.module.trade.order.domain.model.valueobject.Address;
import com.zp.gmall.module.trade.order.domain.model.valueobject.Money;
import com.zp.gmall.module.trade.order.infrastructure.persistence.entity.OrderItemPO;
import com.zp.gmall.module.trade.order.infrastructure.persistence.entity.OrderPO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单领域对象与持久化对象转换器。
 *
 * <h3>位置</h3>
 * 属于基础设施层的防腐适配器，负责将持久化表示与领域表示隔离。
 * 修改数据库表结构时只需修改此转换器，不影响领域层。
 *
 * <h3>关键点</h3>
 * 将 PO 转为领域对象时，必须通过聚合根的工厂方法重建，
 * 不能绕过业务规则直接 set 属性。
 */
public final class OrderConverter {

    private OrderConverter() {
    }

    /**
     * 领域对象 -> 持久化对象
     */
    public static OrderPO toPO(Order order) {
        if (order == null) {
            return null;
        }
        OrderPO po = new OrderPO();
        po.setId(order.getId() != null ? order.getId().getValue() : null);
        po.setOrderNo(order.getOrderNo());
        po.setMemberId(order.getMemberId());
        po.setStatus(order.getStatus() != null ? order.getStatus().name() : null);

        // 地址平铺
        if (order.getReceiverAddress() != null) {
            Address addr = order.getReceiverAddress();
            po.setReceiverProvince(addr.getProvince());
            po.setReceiverCity(addr.getCity());
            po.setReceiverRegion(addr.getRegion());
            po.setReceiverDetail(addr.getDetail());
            po.setReceiverPhone(addr.getPhone());
        }

        // 金额
        po.setTotalAmount(order.getTotalAmount() != null ? order.getTotalAmount().getAmount() : null);
        po.setFreightAmount(order.getFreightAmount() != null ? order.getFreightAmount().getAmount() : null);
        po.setPayAmount(order.getPayAmount() != null ? order.getPayAmount().getAmount() : null);
        po.setDiscountAmount(order.getDiscountAmount() != null ? order.getDiscountAmount().getAmount() : null);

        // 支付/物流
        po.setPaidTime(order.getPaidTime());
        po.setPaymentNo(order.getPaymentNo());
        po.setShippedTime(order.getShippedTime());
        po.setTrackingNo(order.getTrackingNo());
        po.setLogisticsCompany(order.getLogisticsCompany());
        po.setRemark(order.getRemark());

        return po;
    }

    /**
     * 领域对象 -> 订单项PO列表
     */
    public static List<OrderItemPO> toItemPOList(Order order) {
        if (order.getItems() == null) {
            return List.of();
        }
        return order.getItems().stream()
                .map(OrderConverter::toItemPO)
                .collect(Collectors.toList());
    }

    private static OrderItemPO toItemPO(OrderItem item) {
        OrderItemPO po = new OrderItemPO();
        po.setId(item.getId() != null ? item.getId().getValue() : null);
        po.setOrderId(item.getOrderId() != null ? item.getOrderId().getValue() : null);
        po.setSkuId(item.getSkuId());
        po.setProductName(item.getProductName());
        po.setProductImage(item.getProductImage());
        po.setQuantity(item.getQuantity());
        po.setUnitPrice(item.getUnitPrice() != null ? item.getUnitPrice().getAmount() : null);
        po.setSubtotal(item.getSubtotal() != null ? item.getSubtotal().getAmount() : null);
        return po;
    }

    /**
     * 持久化对象 -> 领域对象。
     * 注意：此方法返回的 Order 仅为数据载体，不触发领域事件。
     * 需配合 OrderItemPO 列表完成聚合重建。
     */
    public static Order toDomain(OrderPO po, List<OrderItemPO> itemPOs) {
        if (po == null) {
            return null;
        }

        // 注意：toDomain 重建的 Order 不通过工厂方法 create()，因为那是用于"新建"的。
        // 从数据库重建时，需要一种"重建"机制。这里通过反射或 package-private 方法处理。
        // 此处简化为一个内部重建逻辑，实际项目可设计专门的 reconstruct() 方法。

        List<OrderItem> items = new ArrayList<>();
        if (itemPOs != null) {
            items = itemPOs.stream()
                    .map(OrderConverter::toItemDomain)
                    .collect(Collectors.toList());
        }

        // 使用包级私有的重建方法（或通过反射框架如 MapStruct）
        return reconstructDomain(po, items);
    }

    private static OrderItem toItemDomain(OrderItemPO po) {
        if (po == null) {
            return null;
        }
        return new OrderItem(
                new OrderItemId(po.getId()),
                new OrderId(po.getOrderId()),
                po.getSkuId(),
                po.getProductName(),
                po.getProductImage(),
                po.getQuantity(),
                Money.cny(po.getUnitPrice())
        );
    }

    /**
     * 从 PO 数据重建领域对象。
     * 由于 Order 是非 final 类（需兼容 MyBatis 等框架），
     * 可以通过包级可见或专门的重建方法进行。
     */
    private static Order reconstructDomain(OrderPO po, List<OrderItem> items) {
        // 实际项目中推荐使用 MapStruct 或类似框架处理这种双向映射
        if (po == null) {
            return null;
        }

        Address address = new Address(
                po.getReceiverProvince(),
                po.getReceiverCity(),
                po.getReceiverRegion(),
                po.getReceiverDetail(),
                po.getReceiverPhone()
        );

        Money totalAmount = po.getTotalAmount() != null ? Money.cny(po.getTotalAmount()) : Money.zero();
        Money freightAmount = po.getFreightAmount() != null ? Money.cny(po.getFreightAmount()) : Money.zero();
        Money payAmount = po.getPayAmount() != null ? Money.cny(po.getPayAmount()) : Money.zero();
        Money discountAmount = po.getDiscountAmount() != null ? Money.cny(po.getDiscountAmount()) : Money.zero();

        // 使用静态工厂重建（不触发领域事件）
        return Order.reconstruct(
                new OrderId(po.getId()),
                po.getOrderNo(),
                po.getMemberId(),
                OrderStatus.valueOf(po.getStatus()),
                items,
                address,
                totalAmount, freightAmount, payAmount, discountAmount,
                po.getPaidTime(), po.getPaymentNo(),
                po.getShippedTime(), po.getTrackingNo(), po.getLogisticsCompany(),
                po.getRemark(),
                po.getCreateTime(), po.getUpdateTime()
        );
    }
}
