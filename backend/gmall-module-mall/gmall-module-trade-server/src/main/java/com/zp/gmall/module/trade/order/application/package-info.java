/**
 * 应用层。
 *
 * <h2>职责</h2>
 * <ul>
 *   <li>{@code service} - 应用服务，编排领域对象完成用例</li>
 *   <li>{@code command} - 命令对象，封装用例的输入参数</li>
 *   <li>{@code query} - 查询对象，封装查询参数</li>
 *   <li>{@code assembler} - 装配器，DTO 与领域对象之间的转换</li>
 * </ul>
 *
 * <h2>约束</h2>
 * 应用层不包含核心业务规则，核心规则由领域层负责。
 */
package com.zp.gmall.module.trade.order.application;
