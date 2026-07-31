package com.zp.gmall.module.trade.order.application.service;

import com.zp.gmall.framework.common.ddd.DomainEvent;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.trade.order.application.assembler.OrderAssembler;
import com.zp.gmall.module.trade.order.application.command.CreateOrderCommand;
import com.zp.gmall.module.trade.order.application.query.OrderPageQuery;
import com.zp.gmall.module.trade.order.domain.model.order.Order;
import com.zp.gmall.module.trade.order.domain.model.order.OrderId;
import com.zp.gmall.module.trade.order.domain.repository.OrderRepository;
import com.zp.gmall.module.trade.order.domain.service.OrderDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单应用服务。
 *
 * <h3>职责边界</h3>
 * <ul>
 *   <li>编排领域对象完成用例流程</li>
 *   <li>管理事务边界</li>
 *   <li>发布领域事件（在事务提交后）</li>
 *   <li>不包含核心业务规则（核心规则在领域层）</li>
 * </ul>
 *
 * <h3>调用约束</h3>
 * <ul>
 *   <li>应用服务之间不应互相调用</li>
 *   <li>应用层不直接操作持久化框架</li>
 *   <li>通过仓储接口访问领域对象</li>
 * </ul>
 */
@Service
public class OrderAppService {

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final OrderAssembler orderAssembler;

    public OrderAppService(OrderRepository orderRepository,
                           OrderDomainService orderDomainService,
                           OrderAssembler orderAssembler) {
        this.orderRepository = orderRepository;
        this.orderDomainService = orderDomainService;
        this.orderAssembler = orderAssembler;
    }

    /**
     * 创建订单用例。
     * 编排: 生成编号 -> 创建领域对象 -> 持久化 -> 发布事件
     */
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(CreateOrderCommand command) {
        // 1. 生成订单编号
        String orderNo = orderDomainService.generateOrderNo();

        // 2. 组装领域对象（基础设施生成的ID暂用雪花算法模拟）
        OrderId orderId = new OrderId(System.currentTimeMillis());
        Order order = orderAssembler.toDomain(command, orderId, orderNo);

        // 3. 持久化聚合根（聚合内强一致性由事务保证）
        orderRepository.save(order);

        // 4. 发布领域事件（事务提交后由事件机制负责分发）
        List<DomainEvent> events = order.pollDomainEvents();
        publishEvents(events);

        return orderId.getValue();
    }

    /**
     * 支付订单用例。
     */
    @Transactional(rollbackFor = Exception.class)
    public void payOrder(Long orderId, String paymentNo) {
        Order order = orderRepository.findById(new OrderId(orderId))
                .orElseThrow(() -> new IllegalArgumentException("订单不存在: " + orderId));

        orderDomainService.verifyPayable(order, orderRepository);

        // 领域行为: 支付
        order.pay(paymentNo, null);

        // 持久化
        orderRepository.save(order);

        // 发布领域事件
        List<DomainEvent> events = order.pollDomainEvents();
        publishEvents(events);
    }

    /**
     * 取消订单用例。
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(Long orderId, String reason) {
        Order order = orderRepository.findById(new OrderId(orderId))
                .orElseThrow(() -> new IllegalArgumentException("订单不存在: " + orderId));

        // 领域行为: 取消
        order.cancel(reason);

        // 持久化
        orderRepository.save(order);

        // 发布领域事件
        List<DomainEvent> events = order.pollDomainEvents();
        publishEvents(events);
    }

    /**
     * 发货用例。
     */
    @Transactional(rollbackFor = Exception.class)
    public void shipOrder(Long orderId, String trackingNo, String logisticsCompany) {
        Order order = orderRepository.findById(new OrderId(orderId))
                .orElseThrow(() -> new IllegalArgumentException("订单不存在: " + orderId));

        // 领域行为: 发货
        order.ship(trackingNo, logisticsCompany);

        // 持久化
        orderRepository.save(order);

        // 发布领域事件
        List<DomainEvent> events = order.pollDomainEvents();
        publishEvents(events);
    }

    /**
     * 查询订单分页用例。
     */
    public PageResult<Order> queryOrderPage(OrderPageQuery query) {
        OrderRepository.OrderPageResult result;
        if (query.getMemberId() != null) {
            result = orderRepository.findByMemberId(query.getMemberId(), query.getPageParam());
        } else if (query.getStatus() != null) {
            result = orderRepository.findByStatus(query.getStatus(), query.getPageParam());
        } else {
            // 默认查询全部（实际项目通过分页插件实现）
            return PageResult.empty((long) query.getPageParam().getPageNo(), (long) query.getPageParam().getPageSize());
        }
        return new PageResult<>(result.list(), result.total(),
                (long) query.getPageParam().getPageNo(), (long) query.getPageParam().getPageSize());
    }

    /**
     * 查询订单详情用例。
     */
    public Order getOrderDetail(Long orderId) {
        return orderRepository.findById(new OrderId(orderId))
                .orElseThrow(() -> new IllegalArgumentException("订单不存在: " + orderId));
    }

    /**
     * 发布领域事件（占位方法）。
     * 实际项目中可对接 Spring ApplicationEvent、RocketMQ、Kafka 等消息中间件。
     */
    private void publishEvents(List<DomainEvent> events) {
        if (events.isEmpty()) {
            return;
        }
        // TODO: 接入事件总线/消息队列
        events.forEach(event -> {
            // 示例: eventPublisher.publish(event);
        });
    }
}
