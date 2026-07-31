package com.zp.gmall.framework.common.ddd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 聚合根工具类，提供领域事件管理的默认实现。
 * 聚合根通过组合（而非继承）使用此工具类来管理领域事件。
 */
public final class AggregateRootUtils {

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    /**
     * 注册一个领域事件
     */
    public void registerEvent(DomainEvent event) {
        this.domainEvents.add(event);
    }

    /**
     * 获取并清除所有已注册的领域事件
     */
    public List<DomainEvent> pollEvents() {
        if (domainEvents.isEmpty()) {
            return Collections.emptyList();
        }
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return Collections.unmodifiableList(events);
    }

    /**
     * 清除所有领域事件
     */
    public void clearEvents() {
        domainEvents.clear();
    }

    /**
     * 是否存在未处理的领域事件
     */
    public boolean hasEvents() {
        return !domainEvents.isEmpty();
    }
}
