package com.zp.gmall.module.trade.order.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.gmall.module.trade.order.infrastructure.persistence.entity.OrderItemPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项表 MyBatis Mapper。
 */
@Mapper
public interface OrderItemPOMapper extends BaseMapper<OrderItemPO> {
}
