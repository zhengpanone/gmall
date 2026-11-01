/**
 * 基础设施层。
 *
 * <h2>包结构</h2>
 * <ul>
 *   <li>{@code persistence.entity} - 持久化对象（PO，与数据库表映射）</li>
 *   <li>{@code persistence.mapper} - MyBatis Mapper 接口</li>
 *   <li>{@code persistence.converter} - 领域对象与 PO 的双向转换器（防腐层）</li>
 *   <li>{@code repository} - 仓储接口实现（OrderRepositoryImpl）</li>
 *   <li>{@code config} - 基础设施配置</li>
 * </ul>
 *
 * <h2>依赖方向</h2>
 * 基础设施层依赖领域层（实现领域层定义的接口），
 * 领域层绝不依赖基础设施层。
 */
package com.zp.gmall.module.trade.order.infrastructure;
