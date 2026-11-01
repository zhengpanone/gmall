package com.zp.gmall.module.trade.order.infrastructure.persistence.mapper;

import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.module.trade.order.infrastructure.persistence.entity.OrderPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表 MyBatis Mapper。
 * 继承 BaseMapperX 获得项目统一的增强 CRUD 能力。
 */
@Mapper
public interface OrderPOMapper extends BaseMapperX<OrderPO> {
}
