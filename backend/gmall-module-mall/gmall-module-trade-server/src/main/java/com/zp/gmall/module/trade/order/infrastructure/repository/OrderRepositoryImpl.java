package com.zp.gmall.module.trade.order.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zp.gmall.framework.common.domain.dto.SortablePageParam;
import com.zp.gmall.module.trade.order.domain.model.order.Order;
import com.zp.gmall.module.trade.order.domain.model.order.OrderId;
import com.zp.gmall.module.trade.order.domain.repository.OrderRepository;
import com.zp.gmall.module.trade.order.infrastructure.persistence.converter.OrderConverter;
import com.zp.gmall.module.trade.order.infrastructure.persistence.entity.OrderItemPO;
import com.zp.gmall.module.trade.order.infrastructure.persistence.entity.OrderPO;
import com.zp.gmall.module.trade.order.infrastructure.persistence.mapper.OrderItemPOMapper;
import com.zp.gmall.module.trade.order.infrastructure.persistence.mapper.OrderPOMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 订单仓储实现（基础设施层）。
 *
 * <h3>职责</h3>
 * 实现领域层定义的 OrderRepository 接口，使用 MyBatis-Plus 完成持久化操作。
 * 负责领域对象 (Order) 与持久化对象 (OrderPO) 的转换。
 *
 * <h3>聚合持久化</h3>
 * 订单聚合包含 Order（根）和 OrderItem（内部实体），
 * 仓储实现负责以聚合为单位进行整体持久化，保证聚合内的一致性。
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderPOMapper orderPOMapper;
    private final OrderItemPOMapper orderItemPOMapper;

    public OrderRepositoryImpl(OrderPOMapper orderPOMapper,
                               OrderItemPOMapper orderItemPOMapper) {
        this.orderPOMapper = orderPOMapper;
        this.orderItemPOMapper = orderItemPOMapper;
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        OrderPO po = orderPOMapper.selectById(id.getValue());
        if (po == null) {
            return Optional.empty();
        }
        List<OrderItemPO> itemPOs = orderItemPOMapper.selectList(
                new LambdaQueryWrapper<OrderItemPO>()
                        .eq(OrderItemPO::getOrderId, id.getValue()));
        return Optional.of(OrderConverter.toDomain(po, itemPOs));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Order order) {
        // 1. 保存聚合根
        OrderPO orderPO = OrderConverter.toPO(order);
        boolean exists = orderPOMapper.selectById(orderPO.getId()) != null;
        if (exists) {
            orderPOMapper.updateById(orderPO);
        } else {
            orderPOMapper.insert(orderPO);
        }

        // 2. 保存内部实体（先删后插策略，简化实现）
        if (order.getItems() != null) {
            // 删除旧订单项
            orderItemPOMapper.delete(new LambdaQueryWrapper<OrderItemPO>()
                    .eq(OrderItemPO::getOrderId, orderPO.getId()));
            // 插入新订单项
            List<OrderItemPO> itemPOs = OrderConverter.toItemPOList(order);
            for (OrderItemPO itemPO : itemPOs) {
                if (orderItemPOMapper.selectById(itemPO.getId()) != null) {
                    orderItemPOMapper.updateById(itemPO);
                } else {
                    orderItemPOMapper.insert(itemPO);
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(OrderId id) {
        // 先删除订单项
        orderItemPOMapper.delete(new LambdaQueryWrapper<OrderItemPO>()
                .eq(OrderItemPO::getOrderId, id.getValue()));
        // 再删除订单
        orderPOMapper.deleteById(id.getValue());
    }

    @Override
    public Optional<Order> findByOrderNo(String orderNo) {
        OrderPO po = orderPOMapper.selectOne(OrderPO::getOrderNo, orderNo);
        if (po == null) {
            return Optional.empty();
        }
        List<OrderItemPO> itemPOs = orderItemPOMapper.selectList(
                new LambdaQueryWrapper<OrderItemPO>()
                        .eq(OrderItemPO::getOrderId, po.getId()));
        return Optional.of(OrderConverter.toDomain(po, itemPOs));
    }

    @Override
    public OrderPageResult findByMemberId(Long memberId, SortablePageParam pageParam) {
        var result = orderPOMapper.selectPage(pageParam,
                new LambdaQueryWrapper<OrderPO>()
                        .eq(OrderPO::getMemberId, memberId));
        List<Order> orders = result.getList().stream()
                .map(po -> {
                    List<OrderItemPO> items = orderItemPOMapper.selectList(
                            new LambdaQueryWrapper<OrderItemPO>()
                                    .eq(OrderItemPO::getOrderId, po.getId()));
                    return OrderConverter.toDomain(po, items);
                })
                .toList();
        return new OrderPageResult(orders, result.getTotal());
    }

    @Override
    public OrderPageResult findByStatus(String status, SortablePageParam pageParam) {
        var result = orderPOMapper.selectPage(pageParam,
                new LambdaQueryWrapper<OrderPO>()
                        .eq(OrderPO::getStatus, status));
        List<Order> orders = result.getList().stream()
                .map(po -> {
                    List<OrderItemPO> items = orderItemPOMapper.selectList(
                            new LambdaQueryWrapper<OrderItemPO>()
                                    .eq(OrderItemPO::getOrderId, po.getId()));
                    return OrderConverter.toDomain(po, items);
                })
                .toList();
        return new OrderPageResult(orders, result.getTotal());
    }
}
