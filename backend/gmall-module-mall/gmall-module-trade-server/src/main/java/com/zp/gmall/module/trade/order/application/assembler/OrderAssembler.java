package com.zp.gmall.module.trade.order.application.assembler;

import com.zp.gmall.module.trade.order.application.command.CreateOrderCommand;
import com.zp.gmall.module.trade.order.domain.model.order.Order;
import com.zp.gmall.module.trade.order.domain.model.order.OrderId;
import com.zp.gmall.module.trade.order.domain.model.order.OrderItem;
import com.zp.gmall.module.trade.order.domain.model.order.OrderItemId;
import com.zp.gmall.module.trade.order.domain.model.valueobject.Address;
import com.zp.gmall.module.trade.order.domain.model.valueobject.Money;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单装配器。
 *
 * <h3>职责</h3>
 * 负责应用层 DTO/Command 与领域对象之间的转换。
 * 转换逻辑不包含任何业务规则，只是数据映射。
 *
 * <h3>位置</h3>
 * 放在应用层而非基础设施层，因为这是应用层内部的 DTO 到领域对象的转换，
 * 与持久化无关。
 */
@Component
public class OrderAssembler {

    /**
     * 将创建订单命令转换为订单聚合根。
     * 注意：订单项 ID 在持久化时由数据库生成，此处使用时间戳模拟。
     */
    public Order toDomain(CreateOrderCommand command, OrderId orderId, String orderNo) {
        // 1. 构建收货地址值对象
        Address address = new Address(
                command.getReceiverProvince(),
                command.getReceiverCity(),
                command.getReceiverRegion(),
                command.getReceiverDetail(),
                command.getReceiverPhone()
        );

        // 2. 构建订单项实体列表
        List<OrderItem> items = new ArrayList<>();
        long itemSeq = 0;
        for (CreateOrderCommand.OrderItemCommand itemCmd : command.getItems()) {
            OrderItemId itemId = new OrderItemId(System.currentTimeMillis() + (++itemSeq));
            OrderItem item = new OrderItem(
                    itemId,
                    orderId,
                    itemCmd.getSkuId(),
                    itemCmd.getProductName(),
                    itemCmd.getProductImage(),
                    itemCmd.getQuantity(),
                    Money.cny(itemCmd.getUnitPrice())
            );
            items.add(item);
        }

        // 3. 计算金额
        Money freightAmount = command.getFreightAmount() != null
                ? Money.cny(command.getFreightAmount()) : Money.zero();
        Money discountAmount = command.getDiscountAmount() != null
                ? Money.cny(command.getDiscountAmount()) : Money.zero();

        // 4. 调用聚合根工厂方法创建订单（业务校验在工厂方法内执行）
        return Order.create(orderId, orderNo, command.getMemberId(),
                items, address, freightAmount, discountAmount, command.getRemark());
    }
}
