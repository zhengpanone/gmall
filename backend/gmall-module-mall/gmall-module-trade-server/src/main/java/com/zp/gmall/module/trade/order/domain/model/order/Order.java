package com.zp.gmall.module.trade.order.domain.model.order;

import com.zp.gmall.framework.common.ddd.AggregateRoot;
import com.zp.gmall.framework.common.ddd.AggregateRootUtils;
import com.zp.gmall.framework.common.ddd.DomainEvent;
import com.zp.gmall.module.trade.order.domain.event.OrderCancelledEvent;
import com.zp.gmall.module.trade.order.domain.event.OrderCreatedEvent;
import com.zp.gmall.module.trade.order.domain.event.OrderPaidEvent;
import com.zp.gmall.module.trade.order.domain.event.OrderShippedEvent;
import com.zp.gmall.module.trade.order.domain.model.valueobject.Address;
import com.zp.gmall.module.trade.order.domain.model.valueobject.Money;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 订单聚合根。
 *
 * <h3>业务不变式（Invariants）</h3>
 * <ul>
 *   <li>订单必须有至少一个订单项</li>
 *   <li>订单总额 = 所有订单项小计之和</li>
 *   <li>已支付订单不可修改商品</li>
 *   <li>已取消订单不可修改</li>
 * </ul>
 *
 * <h3>一致性边界</h3>
 * 订单（Order）和订单项（OrderItem）在同一个聚合内，
 * 对订单的任何修改必须保证订单项与总额的一致性（强一致性）。
 * 与其他聚合（库存、支付、物流）通过领域事件实现最终一致性。
 */
public class Order implements AggregateRoot<OrderId> {

    // ==================== 属性 ====================

    /** 订单标识 */
    private OrderId id;

    /** 订单编号（业务号，对外展示） */
    private String orderNo;

    /** 会员ID */
    private Long memberId;

    /** 订单状态 */
    private OrderStatus status;

    /** 订单项列表（聚合内部实体，通过聚合根访问） */
    private List<OrderItem> items;

    /** 收货地址 */
    private Address receiverAddress;

    /** 商品总金额 */
    private Money totalAmount;

    /** 运费 */
    private Money freightAmount;

    /** 实付金额 = totalAmount + freightAmount - discountAmount */
    private Money payAmount;

    /** 优惠金额 */
    private Money discountAmount;

    /** 支付时间 */
    private LocalDateTime paidTime;

    /** 支付流水号 */
    private String paymentNo;

    /** 发货时间 */
    private LocalDateTime shippedTime;

    /** 物流单号 */
    private String trackingNo;

    /** 物流公司 */
    private String logisticsCompany;

    /** 订单备注 */
    private String remark;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

    // ==================== 领域事件管理 ====================

    /** 事件管理器（组合优于继承） */
    private final transient AggregateRootUtils eventUtils = new AggregateRootUtils();

    // ==================== 构造 ====================

    /** 框架/持久化所需的无参构造 */
    protected Order() {
    }

    /**
     * 创建新订单（工厂方法）。
     * 这是创建订单的唯一入口，确保业务不变式被执行。
     */
    public static Order create(OrderId id, String orderNo, Long memberId,
                               List<OrderItem> items, Address receiverAddress,
                               Money freightAmount, Money discountAmount, String remark) {
        // 业务不变式校验
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("订单必须包含至少一个订单项");
        }
        if (receiverAddress == null) {
            throw new IllegalArgumentException("收货地址不能为空");
        }
        if (memberId == null) {
            throw new IllegalArgumentException("会员ID不能为空");
        }

        // 计算金额
        Money totalAmount = items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(Money.zero(), Money::add);

        Money payAmount = totalAmount
                .add(freightAmount != null ? freightAmount : Money.zero())
                .subtract(discountAmount != null ? discountAmount : Money.zero());

        if (!payAmount.isGreaterThanOrEqual(Money.zero())) {
            throw new IllegalArgumentException("实付金额不能为负数");
        }

        Order order = new Order();
        order.id = id;
        order.orderNo = orderNo;
        order.memberId = memberId;
        order.status = OrderStatus.PENDING_PAYMENT;
        order.items = new ArrayList<>(items);
        order.receiverAddress = receiverAddress;
        order.totalAmount = totalAmount;
        order.freightAmount = freightAmount != null ? freightAmount : Money.zero();
        order.discountAmount = discountAmount != null ? discountAmount : Money.zero();
        order.payAmount = payAmount;
        order.remark = remark;
        order.createTime = LocalDateTime.now();
        order.updateTime = order.createTime;

        // 发布领域事件
        order.eventUtils.registerEvent(
                new OrderCreatedEvent(id, memberId, totalAmount));

        return order;
    }

    /**
     * 从持久化存储重建订单（仓库用工厂方法）。
     * 与 create() 不同：不触发领域事件，不执行创建校验。
     * 仅用于从数据库/缓存重建领域对象。
     */
    public static Order reconstruct(OrderId id, String orderNo, Long memberId,
                                    OrderStatus status, List<OrderItem> items,
                                    Address receiverAddress,
                                    Money totalAmount, Money freightAmount,
                                    Money payAmount, Money discountAmount,
                                    LocalDateTime paidTime, String paymentNo,
                                    LocalDateTime shippedTime, String trackingNo,
                                    String logisticsCompany, String remark,
                                    LocalDateTime createTime, LocalDateTime updateTime) {
        Order order = new Order();
        order.id = id;
        order.orderNo = orderNo;
        order.memberId = memberId;
        order.status = status;
        order.items = items != null ? new ArrayList<>(items) : new ArrayList<>();
        order.receiverAddress = receiverAddress;
        order.totalAmount = totalAmount != null ? totalAmount : Money.zero();
        order.freightAmount = freightAmount != null ? freightAmount : Money.zero();
        order.payAmount = payAmount != null ? payAmount : Money.zero();
        order.discountAmount = discountAmount != null ? discountAmount : Money.zero();
        order.paidTime = paidTime;
        order.paymentNo = paymentNo;
        order.shippedTime = shippedTime;
        order.trackingNo = trackingNo;
        order.logisticsCompany = logisticsCompany;
        order.remark = remark;
        order.createTime = createTime;
        order.updateTime = updateTime;
        return order;
    }

    // ==================== 领域行为 ====================

    /**
     * 支付订单。
     * 只有待支付状态的订单可以执行支付操作。
     */
    public void pay(String paymentNo, LocalDateTime paidTime) {
        if (!status.canPay()) {
            throw new IllegalStateException(
                    String.format("订单当前状态[%s]不允许支付", status.getDescription()));
        }
        if (paymentNo == null || paymentNo.isBlank()) {
            throw new IllegalArgumentException("支付流水号不能为空");
        }

        this.status = OrderStatus.PAID;
        this.paymentNo = paymentNo;
        this.paidTime = paidTime != null ? paidTime : LocalDateTime.now();
        this.updateTime = LocalDateTime.now();

        // 发布领域事件
        eventUtils.registerEvent(new OrderPaidEvent(id, this.paidTime, paymentNo));
    }

    /**
     * 取消订单。
     * 待支付和已支付状态的订单可以取消。
     */
    public void cancel(String reason) {
        if (!status.canCancel()) {
            throw new IllegalStateException(
                    String.format("订单当前状态[%s]不允许取消", status.getDescription()));
        }

        this.status = OrderStatus.CANCELLED;
        this.updateTime = LocalDateTime.now();

        // 发布领域事件
        eventUtils.registerEvent(new OrderCancelledEvent(id,
                reason != null ? reason : "用户取消"));
    }

    /**
     * 发货。
     * 只有已支付状态的订单可以发货。
     */
    public void ship(String trackingNo, String logisticsCompany) {
        if (!status.canShip()) {
            throw new IllegalStateException(
                    String.format("订单当前状态[%s]不允许发货", status.getDescription()));
        }
        if (trackingNo == null || trackingNo.isBlank()) {
            throw new IllegalArgumentException("物流单号不能为空");
        }

        this.status = OrderStatus.SHIPPED;
        this.trackingNo = trackingNo;
        this.logisticsCompany = logisticsCompany;
        this.shippedTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();

        // 发布领域事件
        eventUtils.registerEvent(
                new OrderShippedEvent(id, trackingNo, logisticsCompany));
    }

    /**
     * 确认收货。
     * 只有已发货状态的订单可以确认收货。
     */
    public void confirmReceived() {
        if (this.status != OrderStatus.SHIPPED) {
            throw new IllegalStateException(
                    String.format("订单当前状态[%s]不允许确认收货", status.getDescription()));
        }

        this.status = OrderStatus.DELIVERED;
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 发起退款。
     * 已支付或已发货状态的订单可以申请退款。
     */
    public void applyRefund() {
        if (this.status != OrderStatus.PAID && this.status != OrderStatus.SHIPPED) {
            throw new IllegalStateException(
                    String.format("订单当前状态[%s]不允许申请退款", status.getDescription()));
        }

        this.status = OrderStatus.REFUNDING;
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 完成退款。
     */
    public void completeRefund() {
        if (this.status != OrderStatus.REFUNDING) {
            throw new IllegalStateException(
                    String.format("订单当前状态[%s]不允许完成退款", status.getDescription()));
        }

        this.status = OrderStatus.REFUNDED;
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 修改收货地址。
     * 仅待支付和已支付状态的订单可修改。
     */
    public void changeAddress(Address newAddress) {
        if (status.isFinal()) {
            throw new IllegalStateException("终态订单不允许修改地址");
        }
        if (this.status == OrderStatus.SHIPPED) {
            throw new IllegalStateException("已发货订单不允许修改地址");
        }
        if (newAddress == null) {
            throw new IllegalArgumentException("收货地址不能为空");
        }
        this.receiverAddress = newAddress;
        this.updateTime = LocalDateTime.now();
    }

    // ==================== 领域事件查询 ====================

    /**
     * 获取并清除所有未处理的领域事件。
     * 由应用层在持久化后调用，用于事件发布。
     */
    public List<DomainEvent> pollDomainEvents() {
        return eventUtils.pollEvents();
    }

    // ==================== Getter ====================

    @Override
    public OrderId getId() {
        return id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public Long getMemberId() {
        return memberId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Address getReceiverAddress() {
        return receiverAddress;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public Money getFreightAmount() {
        return freightAmount;
    }

    public Money getPayAmount() {
        return payAmount;
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }

    public LocalDateTime getPaidTime() {
        return paidTime;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public LocalDateTime getShippedTime() {
        return shippedTime;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public String getRemark() {
        return remark;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
}
