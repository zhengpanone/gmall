/**
 * 接口层（用户接口）。
 *
 * <h2>职责</h2>
 * <ul>
 *   <li>{@code controller} - REST 控制器，协议适配</li>
 *   <li>{@code dto} - 请求 DTO，HTTP 请求参数封装</li>
 *   <li>{@code vo} - 响应 VO，HTTP 响应数据封装</li>
 * </ul>
 *
 * <h2>约束</h2>
 * 接口层只负责协议转换和参数校验，不包含任何业务逻辑。
 */
package com.zp.gmall.module.trade.order.interfaces;
