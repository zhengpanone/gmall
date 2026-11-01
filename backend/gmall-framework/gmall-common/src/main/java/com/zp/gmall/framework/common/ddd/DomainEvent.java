package com.zp.gmall.framework.common.ddd;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 领域事件抽象基类。
 * 领域事件是领域中已经发生的重要业务事实，用于实现聚合间的最终一致性。
 * 事件一旦发生就不可更改，具有唯一标识和发生时间。
 */
@Getter
public abstract class DomainEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 事件唯一标识 */
    private final String eventId;

    /** 事件发生时间 */
    private final LocalDateTime occurredOn;

    protected DomainEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.occurredOn = LocalDateTime.now();
    }

}
