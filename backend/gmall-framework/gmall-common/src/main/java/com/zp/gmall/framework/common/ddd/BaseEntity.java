package com.zp.gmall.framework.common.ddd;

/**
 * 领域实体基类。
 * 实体具有唯一标识，在其生命周期内标识不变，但属性可变。
 * 实体通过标识（而非属性）进行相等性比较。
 *
 * @param <ID> 实体标识类型
 */
public abstract class BaseEntity<ID extends Identifier> {

    /**
     * 获取实体的唯一标识
     */
    public abstract ID getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseEntity<?> that)) {
            return false;
        }
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : super.hashCode();
    }
}
