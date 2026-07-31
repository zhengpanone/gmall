package com.zp.gmall.module.trade.order.domain.service;

import com.zp.gmall.framework.common.ddd.DomainService;
import com.zp.gmall.module.trade.order.domain.model.order.Order;
import com.zp.gmall.module.trade.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

/**
 * 订单领域服务。
 *
 * <h3>使用场景</h3>
 * 领域服务处理不适合放在单个聚合根内的跨实体/跨聚合业务逻辑。
 * 例如：检查是否存在重复订单、生成订单编号等。
 *
 * <h3>与领域事件的关系</h3>
 * 跨聚合的最终一致性通过领域事件实现，领域服务不直接调用其他仓储。
 */
@DomainService
@Service
public class OrderDomainService {

    /**
     * 生成订单编号。
     * 规则: yyyyMMddHHmmss + 6位随机数
     */
    public String generateOrderNo() {
        String timestamp = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        int random = (int) (Math.random() * 900000) + 100000;
        return timestamp + random;
    }

    /**
     * 检查订单是否可支付。
     * 领域服务封装支付前的跨条件校验逻辑。
     */
    public void verifyPayable(Order order, OrderRepository orderRepository) {
        if (order == null) {
            throw new IllegalArgumentException("订单不存在");
        }
        if (!order.getStatus().canPay()) {
            throw new IllegalStateException("订单状态不允许支付");
        }
    }
}
