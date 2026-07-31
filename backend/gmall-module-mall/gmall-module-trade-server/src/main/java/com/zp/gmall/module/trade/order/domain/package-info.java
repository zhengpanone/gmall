/**
 * 订单领域层。
 *
 * <h2>包结构</h2>
 * <ul>
 *   <li>{@code model.order} - 订单聚合（聚合根 Order、实体 OrderItem、OrderStatus）</li>
 *   <li>{@code model.valueobject} - 值对象（Address、Money、PhoneNumber）</li>
 *   <li>{@code event} - 领域事件（OrderCreatedEvent、OrderPaidEvent 等）</li>
 *   <li>{@code service} - 领域服务（OrderDomainService）</li>
 *   <li>{@code repository} - 仓储接口（OrderRepository）</li>
 * </ul>
 *
 * <h2>设计原则</h2>
 * <ul>
 *   <li>聚合内强一致性：订单与订单项在同一个聚合，通过聚合根保证状态一致性</li>
 *   <li>聚合间最终一致性：通过领域事件与其他限界上下文（库存、支付、物流）通信</li>
 *   <li>依赖倒置：仓储接口由领域层定义，基础设施层实现</li>
 * </ul>
 */
package com.zp.gmall.module.trade.order.domain;
