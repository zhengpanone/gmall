package com.zp.gmall.framework.common.ddd;

/**
 * 聚合根标记接口。
 * 聚合根是领域模型的入口，负责维护聚合内部的一致性边界。
 * 聚合内部的所有操作必须通过聚合根进行。
 *
 * @param <ID> 聚合根标识类型
 */
public interface AggregateRoot<ID extends Identifier> {

    /**
     * 获取聚合根唯一标识
     */
    ID getId();
}
