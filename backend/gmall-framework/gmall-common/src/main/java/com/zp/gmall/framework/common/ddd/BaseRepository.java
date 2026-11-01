package com.zp.gmall.framework.common.ddd;

import java.util.Optional;

/**
 * 仓储接口基类。
 * 仓储负责聚合根的持久化，是领域层定义的接口，
 * 由基础设施层提供具体实现（如 MyBatis、JPA）。
 *
 * @param <T>  聚合根类型
 * @param <ID> 聚合根标识类型
 */
public interface BaseRepository<T extends AggregateRoot<ID>, ID extends Identifier> {

    /**
     * 根据标识查找聚合根
     */
    Optional<T> findById(ID id);

    /**
     * 保存聚合根（新增或更新）
     */
    void save(T aggregate);

    /**
     * 根据标识删除聚合根
     */
    void delete(ID id);
}
