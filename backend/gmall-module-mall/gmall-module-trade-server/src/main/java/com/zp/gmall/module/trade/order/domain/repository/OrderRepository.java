package com.zp.gmall.module.trade.order.domain.repository;

import com.zp.gmall.framework.common.domain.dto.SortablePageParam;
import com.zp.gmall.framework.common.ddd.BaseRepository;
import com.zp.gmall.module.trade.order.domain.model.order.Order;
import com.zp.gmall.module.trade.order.domain.model.order.OrderId;

import java.util.Optional;

/**
 * 订单仓储接口（领域层定义）。
 * 只定义领域层所需的查询和持久化方法，
 * 具体实现由基础设施层的 MyBatis/JPA 提供。
 */
public interface OrderRepository extends BaseRepository<Order, OrderId> {

    /**
     * 根据订单编号查找
     */
    Optional<Order> findByOrderNo(String orderNo);

    /**
     * 根据会员ID分页查询
     */
    OrderPageResult findByMemberId(Long memberId, SortablePageParam pageParam);

    /**
     * 根据订单状态分页查询
     */
    OrderPageResult findByStatus(String status, SortablePageParam pageParam);

    /**
     * 分页查询领域对象的结果封装
     */
    record OrderPageResult(java.util.List<Order> list, Long total) {
        private static final long serialVersionUID = 1L;
    }
}
