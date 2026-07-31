/**
 * DDD（领域驱动设计）基础组件包。
 *
 * <h2>核心抽象</h2>
 * <ul>
 *   <li>{@link com.zp.gmall.framework.common.ddd.AggregateRoot} - 聚合根标记接口</li>
 *   <li>{@link com.zp.gmall.framework.common.ddd.BaseEntity} - 实体基类（基于标识的相等性）</li>
 *   <li>{@link com.zp.gmall.framework.common.ddd.BaseValueObject} - 值对象标记类（基于属性值的相等性）</li>
 *   <li>{@link com.zp.gmall.framework.common.ddd.Identifier} - 标识符接口（类型安全）</li>
 *   <li>{@link com.zp.gmall.framework.common.ddd.DomainEvent} - 领域事件基类</li>
 *   <li>{@link com.zp.gmall.framework.common.ddd.BaseRepository} - 仓储接口（领域层定义）</li>
 *   <li>{@link com.zp.gmall.framework.common.ddd.DomainService} - 领域服务标记注解</li>
 *   <li>{@link com.zp.gmall.framework.common.ddd.AggregateRootUtils} - 聚合根事件管理工具</li>
 * </ul>
 *
 * <h2>设计原则</h2>
 * <ul>
 *   <li>聚合内强一致性：通过聚合根保证状态变更的事务边界</li>
 *   <li>聚合间最终一致性：通过领域事件实现异步解耦</li>
 *   <li>依赖倒置：领域层定义接口，基础设施层提供实现</li>
 * </ul>
 */
package com.zp.gmall.framework.common.ddd;
